package com.thoughtworks.account.controller;

final class ConsolePrompts {
    //    public static final String STARTUP_PROMPT
    //            = "1. 注册\n"
    //            + "2. 登录\n"
    //            + "3. 退出\n"
    //            + "请输入你的选择(1~3)：";
    private ConsolePrompts() {

    }

    public static final String CREATION_PROMPT
            = "请输入注册信息(格式：用户名,手机号,邮箱,密码)：";
    public static final String INVALID_REGISTER_FORMAT
            = "格式错误\n请按正确格式输入注册信息：";
    public static final String INVALID_REGISTER_FIELD
            = "请输入合法的注册信息：";
    public static final String USER_EXISTS_PROMPT
            = "请更换用户名并重新输入注册信息：";
    public static final String CREATION_SUCCESS_TEMPLATE
            = "%s, 恭喜你注册成功！";
    public static final String LOGIN_PROMPT
            = "请输入用户名和密码(格式：用户名,密码)：";
    public static final String INVALID_LOGIN_FORMAT
            = "格式错误\n请按正确格式输入用户名和密码：";
    public static final String INVALID_LOGIN_FIELD
            = "请输入合法的登录信息：";
    public static final String USERNAME_PASSWORD_MISMATCH
            = "密码或用户名错误\n请重新输入用户名和密码：";
    public static final String MAX_TRIES_EXCEEDED_TEMPLATE
            = "您已%d次输错密码，账号被锁定";
    public static final String LOGIN_SUCCESS_TEMPLATE
            = "%s，欢迎回来！\n" +
            "您的手机号是%s，邮箱是%s";
}
