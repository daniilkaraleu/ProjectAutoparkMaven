package Project.Classes.Infrastructure.dto.enums;

public enum SQLPatterns {
    SEQ_NAME ("id_seq"),
    CHECK_TABLE_SQL_PATTERN(
            """
                    SELECT EXISTS (
                    SELECT FROM information_schema.tables
                    WHERE table_schema = 'public'
                    AND table_name = '%s'
                    );"""),
    CHECK_SEQ_SQL_PATTERN (
            """
                    SELECT EXISTS (
                    SELECT FROM information_schema.sequences
                    WHERE sequence_schema = 'public'
                    AND sequence_name = '%s'
                    );"""),
    CREATE_ID_SEQ_PATTERN (
            """
                    CREATE SEQUENCE %S
                    INCREMENT 1
                    START 1;"""),
    CREATE_TABLE_SEQ_PATTERN (
            """
                    CREATE TABLE %s (
                    %s SERIAL ,
                    %s);"""),
    INSERT_SQL_PATTERN (
            """
                    INSERT INTO %s(%s)
                    VALUES (%s)
                    RETURNING %s;"""),
    DELETE_ALL_SQL_PATTERN (
            """
                    TRUNCATE TABLE %s
                    """),
    DELETE_SQL_PATTERN (
            """
                    DELETE FROM %s
                    WHERE id = %s;
                    """);

    SQLPatterns (String pattern){
        this.pattern = pattern;
    }

    public final String pattern;
}
