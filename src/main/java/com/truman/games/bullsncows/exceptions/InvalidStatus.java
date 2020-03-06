package com.truman.games.bullsncows.exceptions;

import com.truman.games.bullsncows.models.Status;

public class InvalidStatus extends Exception {
    private final Status requiredStatus;
    private final Status actualStatus;
    private final String description;

    public InvalidStatus(Status requiredStatus, Status actualStatus, String description) {
        this.requiredStatus = requiredStatus;
        this.actualStatus = actualStatus;
        this.description = description;
    }

    @Override
    public String getMessage() {
        return String.format("Could not perform action: [%s]\nRequired Status: %s\nActual Status: %s",
                description, requiredStatus, actualStatus);
    }
}
