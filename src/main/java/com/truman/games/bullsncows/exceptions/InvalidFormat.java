package com.truman.games.bullsncows.exceptions;

public class InvalidFormat extends Exception {
    public InvalidFormat(int len) {
        super(String.format("Invalid input length: [%d]", len));
    }

    public InvalidFormat(char aChar) {
        super(String.format("Invalid non-digit character: [%c]", aChar));
    }

    public InvalidFormat(String msg) {
        super(msg);
    }
}
