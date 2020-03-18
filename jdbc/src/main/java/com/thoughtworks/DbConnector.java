package com.thoughtworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final int TIME_OUT = 5;
    private final String url;
    private final String userName;
    private final String password;
    private Connection connection;

    public DbConnector(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || !connection.isValid(TIME_OUT)) {
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
