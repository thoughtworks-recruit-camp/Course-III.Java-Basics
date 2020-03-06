package com.truman.games.bullsncows.exceptions;

public class InvalidLocalAnswer extends Exception {
    public InvalidLocalAnswer(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return String.format("Local answer is invalid, caused by -> %s", getCause().getMessage());
    }
}
