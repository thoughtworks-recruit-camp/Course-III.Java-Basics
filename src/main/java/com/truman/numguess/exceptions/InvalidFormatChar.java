package com.thoughtworks.numguess.exceptions;

public final class InvalidFormatChar extends InvalidFormat {
    private final char iChar;

    public InvalidFormatChar(char iChar) {
        this.iChar = iChar;
    }

    public String getMessage() {
        return String.format("Invalid character: [%c]", iChar);
    }
}
