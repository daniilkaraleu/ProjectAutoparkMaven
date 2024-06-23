package Project.Classes.Infrastructure.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public enum SqlFieldType {
    INTEGER(Integer.class, "integer", "%s"),
    LONG(Long.class, "int8","%s"),
    DOUBLE(Double.class, "float8", "%s"),
    STRING(String.class, "varchar(255)", "'%s'"),
    DATE(Date.class, "date", "'%s'");
    private final  Class<?> type;
    private final  String sqlType;
    private final  String insertPattern;

    public Class<?> getType() {
        return type;
    }

    public String getSqlType() {
        return sqlType;
    }

    public String getInsertPattern() {
        return insertPattern;
    }
}
