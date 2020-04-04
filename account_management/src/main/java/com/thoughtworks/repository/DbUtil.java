package com.thoughtworks.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil implements AutoCloseable {
    private static final int TIME_OUT = 5;
    private String url;
    private String userName;
    private String password;
    private Connection connection;

    public DbUtil(ConnectionParams connectionParams) {
        this.url = connectionParams.getUrl();
        this.userName = connectionParams.getUserName();
        this.password = connectionParams.getPassword();
    }

    public void clearTable(String tableName) throws SQLException {
        String sql = String.format("TRUNCATE %s", tableName);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public Connection getConnection() throws SQLException {
        refreshConnection();
        return connection;
    }

    public void refreshConnection() throws SQLException {
        if (connection == null || !connection.isValid(TIME_OUT)) {
            connection = DriverManager.getConnection(url, userName, password);
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
