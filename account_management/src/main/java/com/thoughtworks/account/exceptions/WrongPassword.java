package com.thoughtworks.account.exceptions;

// 此异常提示信息不一定输出，而是与用户不存在信息相互混淆
public class WrongPassword extends Exception {
    public WrongPassword() {
        super("密码错误!");
    }
}
