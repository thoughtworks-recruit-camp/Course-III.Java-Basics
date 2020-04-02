package com.thoughtworks.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil implements AutoCloseable {
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

    public Connection getConnection() throws SQLException {
        refreshConnection();
        return connection;
    }

    public void clear() throws SQLException {
        refreshConnection();
        connection.prepareStatement("TRUNCATE student").execute();
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
