package com.thoughtworks.numguess.exceptions;

public final class InvalidFormatChar extends InvalidFormat {
    private final char _char;

    public InvalidFormatChar(char _char) {
        this._char = _char;
    }

    public String getMessage() {
        return String.format("Invalid character: [%c]", _char);
    }
}
