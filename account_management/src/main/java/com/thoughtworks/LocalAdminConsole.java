package com.thoughtworks;

import com.thoughtworks.account.controller.Console;
import com.thoughtworks.account.errors.ExitEvent;
import com.thoughtworks.repository.ConnectionParams;

import java.sql.SQLException;

public class LocalAdminConsole {
    public static void main(String[] args) throws SQLException {
        ConnectionParams connectionParams = new ConnectionParams(
                "jdbc:mysql://localhost:3306/account_management_demo?serverTimezone=UTC",
                "root",
                "root");

        Console controller = new Console(connectionParams);
        controller.initConnection();
        try {
            controller.run(true);
        } catch (ExitEvent e) {
            System.exit(0);
        }
    }
}
