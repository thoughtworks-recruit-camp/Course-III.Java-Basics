package com.thoughtworks.numguess.exceptions;

public final class InvalidFormatDigit extends InvalidFormat {
    private final int digit;

    public InvalidFormatDigit(int digit) {
        this.digit = digit;
    }

    public String getMessage() {
        return String.format("Duplicate digit: [%d]", digit);
    }
}
