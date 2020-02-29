package com.thoughtworks.numguess.exceptions;

public final class InvalidFormatLength extends InvalidFormat {
    private final int len;

    public InvalidFormatLength(int len) {
        this.len = len;
    }

    public String getMessage() {
        return String.format("Invalid length: [%d]", len);
    }
}
