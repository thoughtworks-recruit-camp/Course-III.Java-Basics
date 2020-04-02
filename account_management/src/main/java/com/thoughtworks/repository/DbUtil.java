package com.thoughtworks.repository;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil implements Closeable {
    private static final int TIME_OUT = 5;
    private final String url;
    private final String userName;
    private final String password;
    private Connection connection;

    public DbUtil(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public void clearTable(String tableName) throws SQLException {
        String format = String.format("TRUNCATE %s", tableName);
        try (PreparedStatement statement = connection.prepareStatement(format)) {
            statement.execute();
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || !connection.isValid(TIME_OUT)) {
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }
}
