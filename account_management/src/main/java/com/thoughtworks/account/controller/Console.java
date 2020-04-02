package com.thoughtworks.account.controller;

import com.thoughtworks.account.AccountManager;
import com.thoughtworks.account.AccountInfo;
import com.thoughtworks.account.Utils;
import com.thoughtworks.account.LoginData;
import com.thoughtworks.account.RegisterData;
import com.thoughtworks.account.errors.*;
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

public class Console {
    private final ScannerFilter in;
    private final PrintStream out;
    private TreeMap<String, Method> menuItems;
    private final AccountManager manager = new AccountManager();

    public Console() {
        this(new BasicScannerFilter(new Scanner(System.in)), System.out);
    }


    public Console(BasicScannerFilter in, PrintStream out) {
        this.in = in;
        this.out = out;
    }
    public Console(InputStream in, OutputStream out) {
        this.in = new BasicScannerFilter(new Scanner(in));
        this.out = new PrintStream (out);
    }

    public void setConnection(Connection connection) {
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
            printMenuItems(isAdmin);
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
            }
        }
    }

    @MenuItem(serial = "3", name = "退出")
    private void exit() throws ExitEvent {
        throw new ExitEvent();
    }

    @MenuItem(serial = "4", name = "Test", isAdmin = true)
    private void test() {
        System.out.println("test");
    }

    private void printMenuItems(boolean isAdmin) {
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
}
