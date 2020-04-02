package com.thoughtworks;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    private static final String HOST = "home.truman.pro";
    private static final int PORT = 25600;
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             InputStream in = socket.getInputStream()) {
            socket.setSoTimeout(1000);
            while (true) {
                readInputAndPrint(in);
                out.println(SC.next());
            }
        }
    }

    private static void readInputAndPrint(InputStream in) {
        Scanner inScanner = new Scanner(in);
        while (true) {
            if (!inScanner.hasNext()) {
                break;
            }
            String message = inScanner.nextLine();
            if (message.equals("EXIT")) {
                System.exit(0);
            }
            System.out.println(message);
        }
    }
}
