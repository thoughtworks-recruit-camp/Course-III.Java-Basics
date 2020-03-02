package com.thoughtworks.numguess.exceptions;

public final class InvalidFormatLength extends InvalidFormat {
    private final int iLen;

    public InvalidFormatLength(int iLen) {
        this.iLen = iLen;
    }

    public String getMessage() {
        return String.format("Invalid length: [%d]", iLen);
    }
}
