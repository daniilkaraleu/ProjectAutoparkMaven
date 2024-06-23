package Project.Classes.Infrastructure.dto;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection getConnection();
}
