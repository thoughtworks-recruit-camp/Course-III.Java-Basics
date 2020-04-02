package com.thoughtworks.account.controller;

import com.thoughtworks.account.errors.ExitEvent;
import com.thoughtworks.repository.DbUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ConsoleServer {
    private final int port;
    private DbUtil dbUtil;
    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;

    public ConsoleServer(int port) {
        this.port = port;
    }

    public void setDbConnection(String url, String userName, String password) {
        dbUrl = url;
        dbUserName = userName;
        dbPassword = password;
    }

    public void run() throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Console server started, waiting for connection.");
        while (true) {
            Socket socket = server.accept();
            new Thread(new ServerThread(socket)).start();
        }
    }

    static class ServerThread implements Runnable {
        private final Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream rawIn = socket.getInputStream();
                 OutputStream out = socket.getOutputStream();
                 DbUtil dbUtil = new DbUtil(dbUrl, dbUserName, dbPassword)) {
                System.out.printf("Connection from: %s:%d\n", socket.getInetAddress().getCanonicalHostName(), socket.getPort());
                Console controller = new Console(rawIn, out);
                controller.setConnection(dbUtil.getConnection());
                try {
                    controller.run();
                } catch (ExitEvent e) {
                    out.write("EXIT".getBytes());
                    out.flush();
                    socket.shutdownOutput();
                    socket.close();
                    System.out.println("Connection closed.");
                }
                controller.setConnection(null);
            } catch (IOException e) {
                System.out.println("Connection lost due to IOException.");
            } catch (SQLException e) {
                System.out.println("Failed connecting to database.");
            } catch (NoSuchElementException e) {
                System.out.println("Connecting closed by client.");
            } catch (Throwable e) {
                System.out.printf("Other error occurred: %s\n", e.getClass().getSimpleName());
            }
        }
    }
}
