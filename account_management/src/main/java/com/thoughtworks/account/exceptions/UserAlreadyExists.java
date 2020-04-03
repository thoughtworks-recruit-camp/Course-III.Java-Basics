package com.thoughtworks.account.exceptions;

public class UserAlreadyExists extends Exception {
    private final String userName;

    public UserAlreadyExists(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getMessage() {
        return String.format("用户名已被使用：%s", getUserName());
    }
}
