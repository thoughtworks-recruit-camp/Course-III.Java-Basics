package com.thoughtworks.numguess.exceptions;

public final class InvalidFormatDigit extends InvalidFormat {
    private final int dDigit;

    public InvalidFormatDigit(int dDigit) {
        this.dDigit = dDigit;
    }

    public String getMessage() {
        return String.format("Duplicate digit: [%d]", dDigit);
    }
}
