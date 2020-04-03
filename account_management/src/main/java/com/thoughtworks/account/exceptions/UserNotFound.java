package com.thoughtworks.account.exceptions;

// 此异常提示信息不一定输出，而是与密码错误信息相互混淆
public class UserNotFound extends Exception {
    private final String userName;

    public UserNotFound(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getMessage() {
        return String.format("用户不存在: %s", getUserName());
    }
}
