package com.thoughtworks.account.controller;

import com.thoughtworks.account.AccountManager;
import com.thoughtworks.account.AccountInfo;
import com.thoughtworks.account.Utils;
import com.thoughtworks.account.LoginData;
import com.thoughtworks.account.RegisterData;
import com.thoughtworks.account.errors.*;
import com.thoughtworks.repository.ConnectionParams;
import com.thoughtworks.repository.DbUtil;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.thoughtworks.account.controller.ConsolePrompts.*;

public class Console implements AutoCloseable {
    private final ScannerFilter in;
    private final PrintStream out;
    private final AccountManager manager = new AccountManager();
    private final DbUtil dbUtil;
    private TreeMap<String, Method> menuItems;
    private Connection connection;

    public Console(BasicScannerFilter in, PrintStream out, ConnectionParams connectionParams) {
        this.in = in;
        this.out = out;
        this.dbUtil = new DbUtil(connectionParams);
    }

    public Console(InputStream in, OutputStream out, ConnectionParams connectionParams) {
        this(new BasicScannerFilter(new Scanner(in)), new PrintStream(out), connectionParams);
    }

    public Console(ConnectionParams connectionParams) {
        this(new BasicScannerFilter(new Scanner(System.in)), System.out, connectionParams);
    }

    public void initConnection() throws SQLException {
        connection = dbUtil.getConnection();
        manager.setConnection(connection);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run(boolean isAdmin) {
        menuItems = Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(MenuItem.class) != null)
                .filter(method -> !method.getAnnotation(MenuItem.class).isAdmin() || isAdmin)  // 按权限过滤
                .collect(Collectors.toMap(
                        method -> method.getAnnotation(MenuItem.class).serial(),
                        Function.identity(),
                        (m1, m2) -> m1,
                        TreeMap::new));
        while (true) {
            printMenuItems();
            String input = in.next();
            handleMainInput(input);
        }
    }

    public void run() {
        run(false);
    }

    @SneakyThrows(IllegalAccessException.class)
    private void handleMainInput(String input) {
        if (menuItems.containsKey(input)) {
            try {
                menuItems.get(input).invoke(this);
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof ExitEvent) {
                    throw new ExitEvent();
                }
            }

        } else {
            out.println("输入错误，请重新选择");
        }
    }

    @MenuItem(serial = "1", name = "注册")
    private void register() throws SQLException {
        out.println(CREATION_PROMPT);
        while (true) {
            try {
                RegisterData registerFields = Utils.parseInput(in.next(), RegisterData.class);
                manager.createNewAccount(registerFields);
                out.println(String.format(CREATION_SUCCESS_TEMPLATE, registerFields.getUserName()));
                break;
            } catch (InvalidFormat invalidFormat) {
                out.println(INVALID_REGISTER_FORMAT);
            } catch (InvalidField invalidField) {
                out.println(invalidField.getMessage());
                out.println(INVALID_REGISTER_FIELD);
            } catch (UserAlreadyExists userAlreadyExists) {
                out.println(userAlreadyExists.getMessage());
                out.println(USER_EXISTS_PROMPT);
            }
        }
    }

    @MenuItem(serial = "2", name = "登录")
    private void login() throws SQLException {
        out.println(LOGIN_PROMPT);
        while (true) {
            try {
                LoginData loginFields = Utils.parseInput(in.next(), LoginData.class);
                printAccountInfo(Objects.requireNonNull(manager.getAccountInfo(loginFields).orElse(null)));
                break;
            } catch (InvalidFormat invalidFormat) {
                out.println(INVALID_LOGIN_FORMAT);
            } catch (InvalidField invalidField) {
                out.println(invalidField.getMessage());
                out.println(INVALID_LOGIN_FIELD);
            } catch (UserNotFound | WrongPassword mismatch) {
                out.println(USERNAME_PASSWORD_MISMATCH);
            } catch (MaxTriesExceeded maxTriesExceeded) {
                out.println(maxTriesExceeded.getMessage());
                break;
            }
        }
    }

    @MenuItem(serial = "3", name = "退出")
    private void exit() throws ExitEvent {
        throw new ExitEvent();
    }

    @MenuItem(serial = "4", name = "清除所有数据", isAdmin = true)
    private void truncate() throws SQLException {
        if (isVerified()) {
            dbUtil.clearTable("account_info");
            dbUtil.clearTable("account_security");
        }
    }

    @MenuItem(serial = "5", name = "重置用户密码尝试次数", isAdmin = true)
    private void reset() throws SQLException {
        out.println("请输入需要重置密码尝试次数的用户名：");
        String input = in.next();
        try {
            manager.resetTriesLeft(input);
            out.println("操作成功");
        } catch (UserNotFound e) {
            out.println(e.getMessage());
            out.println("操作失败");
        }
    }

    private void printMenuItems() {
        out.println();
        menuItems.forEach((key, value) ->
                out.printf("%s: %s\n",
                        key,
                        value.getAnnotation(MenuItem.class).name()));
        out.println("请输入你的选择：");
    }

    private void printAccountInfo(AccountInfo info) {
        out.println(String.format(
                LOGIN_SUCCESS_TEMPLATE,
                info.getUserName(),
                info.getPhoneNumber(),
                info.getEMail()));
    }

    private boolean isVerified() {
        String verifyCode = String.valueOf(Math.abs(new Random().nextInt()));
        out.printf("请准确输入下列数字并回车以确认操作: [%s]\n", verifyCode);
        if (in.next().equals(verifyCode)) {
            out.println("操作成功");
            return true;
        }
        out.println("确认失败，已取消操作");
        return false;
    }

    @Override
    public void close() throws SQLException {
        connection = null;
        manager.close();
        dbUtil.close();
    }
}
