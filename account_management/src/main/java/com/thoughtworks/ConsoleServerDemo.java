package com.thoughtworks;

import com.thoughtworks.account.controller.ConsoleServer;

import java.io.IOException;

public class ConsoleServerDemo {
    public static void main(String[] args) throws IOException {
        ConsoleServer server = new ConsoleServer(25600);
        server.setDbConnection("jdbc:mysql://localhost:3306/account_management_demo?serverTimezone=UTC",
                "root",
                "root");
        server.run();
    }
}
