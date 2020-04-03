package com.thoughtworks.repository;

public class ConnectionParams {
    private final String url;
    private final String userName;
    private final String password;

    public ConnectionParams(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
