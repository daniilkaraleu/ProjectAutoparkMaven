package Project.Classes.Infrastructure.dto.impl;

import Project.Classes.Infrastructure.core.annotations.Property;
import Project.Classes.Infrastructure.dto.ConnectionFactory;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryImpl implements ConnectionFactory {
    @Property("url")
    private String url;
    @Property("user")
    private String user;
    @Property("password")
    private String password;
    private Connection connection;
    @SneakyThrows
    @Override
    public Connection getConnection() {
        if (connection == null) {
            return connection = DriverManager.getConnection(url,user,password);
        }
        return connection;
    }
}
