package com.thoughtworks.account.errors;

public class MaxTriesExceeded extends Exception {
    private final int maxRetries;

    public MaxTriesExceeded(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    @Override
    public String getMessage() {
        return String.format("您已%d次输错密码，账号被锁定", getMaxRetries());
    }
}
