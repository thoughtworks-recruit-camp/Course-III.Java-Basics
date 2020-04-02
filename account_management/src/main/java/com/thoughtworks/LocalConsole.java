package com.thoughtworks;

import com.thoughtworks.account.controller.Console;
import com.thoughtworks.account.errors.ExitEvent;
import com.thoughtworks.repository.DbUtil;

import java.sql.SQLException;

public class LocalConsole {

    public static void main(String[] args) throws SQLException {
        DbUtil connector = new DbUtil("jdbc:mysql://localhost:3306/account_management_demo?serverTimezone=UTC",
                "root",
                "root");
        Console controller = new Console();
        controller.setConnection(connector.getConnection());
        try {
            controller.run(true);
        } catch (ExitEvent e) {
            System.exit(0);
        }
    }
}

