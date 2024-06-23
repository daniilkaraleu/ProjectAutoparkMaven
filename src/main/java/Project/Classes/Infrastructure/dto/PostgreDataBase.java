package Project.Classes.Infrastructure.dto;

import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.annotations.ID;
import Project.Classes.Infrastructure.dto.annotations.Table;
import Project.Classes.Infrastructure.dto.enums.SqlFieldType;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class PostgreDataBase {
    private static final String SEQ_NAME = "id_seq";
    private static final String CHECK_TABLE_SQL_PATTERN =
            """
                    SELECT EXISTS (
                    SELECT FROM information_schema.tables
                    WHERE table_schema = 'public'
                    AND table_name = '%s'
                    );""";
    private static final String CHECK_SEQ_SQL_PATTERN =
            """
                    SELECT EXISTS (
                    SELECT FROM information_schema.sequences
                    WHERE sequence_schema = 'public'
                    AND sequence_name = '%s'
                    );""";
    private static final String CREATE_ID_SEQ_PATTERN =
            """
                    CREATE SEQUENCE %S
                    INCREMENT 1
                    START 1;""";
    private static final String CREATE_TABLE_SEQ_PATTERN =
            """
                    CREATE TABLE %s (
                    %s int8 PRIMARY KEY DEFAULT nextval('%s'),
                    %s);""";
    private static final String INSERT_SQL_PATTERN =
            """
                    INSERT INTO %s(%s)
                    VALUES (%s)
                    RETURNING %s;""";
    @Autowired
    private ConnectionFactory connectionFactory;


    private Map<String, String> classToSql;

    private Map<String, String> insertPatternByClass;
    private Map<String, String> insertByClassPattern;
    @Autowired
    private Context context;

    public PostgreDataBase() {}

    @InitMethod
    public void init() throws NoSuchFieldException {
        classToSql = Arrays.stream(SqlFieldType.values())
                .collect(Collectors.toMap(key -> key.getType().getName(), SqlFieldType::getSqlType));

        insertPatternByClass = Arrays.stream(SqlFieldType.values())
                .collect(Collectors.toMap(key -> key.getType().getName(), SqlFieldType::getInsertPattern));

//        if (!checkId_seqIsPresent()) {
//            createSeq();
//        }

        Set<Class<?>> entitiesSet = getEntities();

        try {
            validateTables(entitiesSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        checkOrCreateTable(entitiesSet);

        insertByClassPattern = entitiesSet.stream().collect(Collectors.toMap(Class::getName, this::getInsertSQLRequest));

    }

    private boolean checkId_seqIsPresent(String seqName) {
        String sql = String.format(CHECK_SEQ_SQL_PATTERN, seqName);

        try (Statement statement = connectionFactory.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            resultSet.next();
            return resultSet.getBoolean("exists");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSeq(String seqName) {
        final String sql = String.format(CREATE_ID_SEQ_PATTERN, seqName);
        if (checkId_seqIsPresent(seqName))
            return;

        try (Statement statement = connectionFactory
                .getConnection()
                .createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Class<?>> getEntities() {
        Reflections reflections = context
                .getConfig()
                .getScanner()
                .getReflections();


        return reflections.getTypesAnnotatedWith(Table.class);
    }

    private void validateTables(Set<Class<?>> set) throws SQLException {
        for (Class<?> entity : set) {
            if (Arrays.stream(entity.getDeclaredFields()).noneMatch(field -> field.isAnnotationPresent(ID.class))) {
                throw new SQLException(String.format("Table %s has no Id field", entity.getSimpleName()));
            }

            long amountOfColumns = Arrays.stream(entity.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Column.class))
                    .distinct()
                    .count();

            if ( amountOfColumns != entity.getDeclaredFields().length - 1) {
                throw new SQLException(String.format("Table %s has repeating fields", entity.getSimpleName()));
            }
        }
    }

    private void checkOrCreateTable(Set<Class<?>> set) {
        for (Class<?> entity : set) {
            String sql = String.format(CHECK_TABLE_SQL_PATTERN, entity.getAnnotation(Table.class).name());

            try (Statement statement = connectionFactory.getConnection().createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.next();
                if (!resultSet.getBoolean("exists")) {
                    createTable(entity);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createTable(Class<?> type) {
        String tableName = type.getAnnotation(Table.class).name();
        String seqName = "id_seq_" + tableName;
        String fields = "";
        String idField;
        Field[] privateFields = type.getDeclaredFields();

        Field[] columns = Arrays.stream(privateFields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .toArray(Field[]::new);

        try {
            idField = type.getDeclaredField("id").getAnnotation(ID.class).name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        StringBuilder stb = new StringBuilder();
        for (Field column : columns) {
            Column annotation = column.getAnnotation(Column.class);

            stb.append(annotation.name()).append(" ");
            stb.append(classToSql.get(column.getType().getName()));
            stb.append(annotation.nullable() ? " NOT NULL" : " NULL");
            stb.append(annotation.unique() ? " UNIQUE,\n" : ",\n");


        }
        stb.deleteCharAt(stb.lastIndexOf(","));
        fields = stb.toString();

        createSeq(seqName);

        String sql = String.format(CREATE_TABLE_SEQ_PATTERN, tableName, idField, seqName, fields);

        try (Statement statement = connectionFactory.getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getInsertSQLRequest(Class<?> clazz) {
        String tableName = clazz.getAnnotation(Table.class).name();
        String fields = "";
        String values = "";
        String idFieldName;

        try {
            idFieldName = clazz.getDeclaredField("id").getAnnotation(ID.class).name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        StringBuilder stbFields = new StringBuilder();
        StringBuilder stbValues = new StringBuilder();

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    stbFields.append(field.getName()).append(", ");
                    stbValues.append(insertPatternByClass.get(field.getType().getName())).append(", ");
                });

        fields = stbFields.deleteCharAt(stbFields.lastIndexOf(",")).toString().trim();
        values = stbValues.deleteCharAt(stbValues.lastIndexOf(",")).toString().trim();

        String sql = String.format(INSERT_SQL_PATTERN, tableName, fields, values, idFieldName);
        return sql;
    }

    @SneakyThrows
    public Long save(Object obj) {
        String idFieldName = obj.getClass().getDeclaredField("id").getAnnotation(ID.class).name();
        long id;

        Object[] values = Arrays.stream(obj
                        .getClass()
                        .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(Object[]::new);

        String sql = String.format(insertByClassPattern.get(obj.getClass().getName()), values);

        try (Statement statement = connectionFactory.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            resultSet.next();
            id = resultSet.getLong(idFieldName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Field field = obj.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(obj, id);

        return id;
    }

    public <T> Optional<T> get(Long id, Class<T> clazz) {
        String tableName = clazz.getAnnotation(Table.class).name();
        String idField;
        String sql;

        try {
            idField = clazz.getDeclaredField("id").getAnnotation(ID.class).name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        sql = "SELECT * FROM " + tableName + " WHERE " + idField + " = " + id;

        try (Statement statement = connectionFactory.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            resultSet.next();
            return Optional.of(makeObject(resultSet,clazz));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public <T> List<T> getAll(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(Table.class))
            throw new SQLException(String.format("There's no %s table in DataBase", clazz.getSimpleName()));

        String tableName = clazz.getAnnotation(Table.class).name();
        String sql = "SELECT * FROM " + tableName;
        List<T> objectsList = new ArrayList<>();

        try(Statement statement = connectionFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){

         while (resultSet.next()) {
             objectsList.add(makeObject(resultSet, clazz));
         }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return objectsList;
    }

    @SneakyThrows
    private <T> T makeObject(ResultSet resultSet, Class<T> clazz) {
        T object = clazz.getConstructor().newInstance();

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ID.class))
                .forEach(field -> {field.setAccessible(true);
                    try {
                        field.set(object, resultSet.getObject(field.getAnnotation(ID.class).name()));
                    } catch (IllegalAccessException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {field.setAccessible(true);
                    try {
                        field.set(object, resultSet.getObject(field.getAnnotation(Column.class).name()));
                    } catch (IllegalAccessException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

        return object;
    }

}
