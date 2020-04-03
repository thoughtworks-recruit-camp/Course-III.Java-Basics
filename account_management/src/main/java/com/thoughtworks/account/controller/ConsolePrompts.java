package com.thoughtworks.account.controller;

import java.text.MessageFormat;
import java.util.Formatter;

final class ConsolePrompts {


    private ConsolePrompts() {
    }

    // 主菜单提示
    //    public static final String STARTUP_PROMPT
    //            = "1. 注册\n"
    //            + "2. 登录\n"
    //            + "3. 退出\n"
    //            + "请输入你的选择(1~3)：";
    public static final String WRONG_OPTION
            = "输入错误，请重新选择:";

    // 输入提示
    public static final String CREATION
            = "请输入注册信息(格式：用户名,手机号,邮箱,密码)：";
    public static final String LOGIN
            = "请输入用户名和密码(格式：用户名,密码)：";

    // 注册
    public static final String INVALID_REGISTER_FORMAT
            = "格式错误\n请按正确格式输入注册信息：";
    public static final String USER_EXISTS
            = "请更换用户名并重新输入注册信息：";
    public static final String INVALID_REGISTER_FIELD
            = "请输入合法的注册信息：";
    public static final MessageFormat CREATION_SUCCESS_TEMPLATE  // 消息模板
            = new MessageFormat("{0}, 恭喜你注册成功！");

    //  登录
    public static final String INVALID_LOGIN_FORMAT
            = "格式错误\n请按正确格式输入用户名和密码：";
    public static final String INVALID_LOGIN_FIELD
            = "请输入合法的登录信息：";
    public static final String MISMATCH
            = "密码或用户名错误\n请重新输入用户名和密码：";
    public final static MessageFormat LOGIN_SUCCESS_TEMPLATE
            = new MessageFormat("{0}，欢迎回来！\n您的手机号是{1}，邮箱是{2}");
}
