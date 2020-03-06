package com.truman.games.bullsncows.exceptions;

public class InvalidInput extends Exception {
    public InvalidInput(InvalidFormat invalidFormat) {
        super(invalidFormat);
    }

    @Override
    public String getMessage() {
        return String.format("Wrong input, caused by -> %s", getCause().getMessage());
    }
}
