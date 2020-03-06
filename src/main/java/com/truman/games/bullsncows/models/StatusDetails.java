package com.truman.games.bullsncows.models;

public class StatusDetails {
    private final int triesMax;
    private final int triesLeft;
    private final Status status;
    private final long lastActiveTime;

    public long getLastActiveTime() {
        return lastActiveTime;
    }

    public StatusDetails(int triesMax, int triesLeft, Status status, long lastActiveTime) {
        this.triesMax = triesMax;
        this.triesLeft = triesLeft;
        this.status = status;
        this.lastActiveTime = lastActiveTime;
    }

    public int getTriesLeft() {
        return triesLeft;
    }

    public Status getStatus() {
        return status;
    }

    public int getTriesMax() {
        return triesMax;
    }
}
