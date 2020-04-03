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
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Console implements AutoCloseable {
    public static final MessageFormat CREATION_SUCCESS_FORMATTER  // 消息模板
            = new MessageFormat("{0}, 恭喜你注册成功！");
    public final static MessageFormat LOGIN_SUCCESS_FORMATTER
            = new MessageFormat("{0}，欢迎回来！\n您的手机号是{1}，邮箱是{2}");
    private final ScannerFilter in;
    private final PrintStream out;
    private final AccountManager manager = new AccountManager();
    private final DbUtil dbUtil;
    private final Printer printer = new Printer(this);
    private TreeMap<String, Method> menuItems;

    public Console(BasicScannerFilter in, PrintStream out, ConnectionParams connectionParams) {
        this.in = in;
        this.out = out;
        this.dbUtil = new DbUtil(connectionParams);
    }

    // for socket server
    public Console(InputStream in, OutputStream out, ConnectionParams connectionParams) {
        this(new BasicScannerFilter(new Scanner(in)), new PrintStream(out), connectionParams);
    }

    //for local console
    public Console(ConnectionParams connectionParams) {
        this(new BasicScannerFilter(new Scanner(System.in)), System.out, connectionParams);
    }

    public void initConnection() throws SQLException {
        manager.setConnection(dbUtil.getConnection());
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run(boolean isAdmin) {
        getMenuItems(isAdmin);
        while (true) {
            printer.printMenuItems();
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
                if (e.getTargetException() instanceof ExitEvent) {  // 对反射调用方法抛出的ExitEvent进行解包
                    throw new ExitEvent();
                }
            }
        } else {
            out.println("输入错误，请重新选择:");
        }
    }

    @MenuItem(serial = "1", name = "注册")
    private void register() throws SQLException {
        out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)：");
        while (true) {
            try {
                RegisterData registerFields = Utils.parseInput(in.next(), RegisterData.class);
                manager.createNewAccount(registerFields);
                printRegisterSuccess(registerFields.getUserName());
                break;
            } catch (InvalidFormat invalidFormat) {
                out.println("格式错误\n请按正确格式输入注册信息：");
            } catch (InvalidField invalidField) {
                out.println(invalidField.getMessage());
                out.println("请输入合法的注册信息：");
            } catch (UserAlreadyExists userAlreadyExists) {
                out.println(userAlreadyExists.getMessage());
                out.println("请更换用户名并重新输入注册信息：");
            }
        }
    }


    @MenuItem(serial = "2", name = "登录")
    private void login() throws SQLException {
        out.println("请输入用户名和密码(格式：用户名,密码)：");
        while (true) {
            try {
                LoginData loginFields = Utils.parseInput(in.next(), LoginData.class);
                printAccountInfo(Objects.requireNonNull(manager.getAccountInfo(loginFields).orElse(null)));
                break;
            } catch (InvalidFormat invalidFormat) {
                out.println("格式错误\n请按正确格式输入用户名和密码：");
            } catch (InvalidField invalidField) {
                out.println(invalidField.getMessage());
                out.println("请输入合法的登录信息：");
            } catch (UserNotFound | WrongPassword mismatch) {
                out.println("密码或用户名错误\n请重新输入用户名和密码：");
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

    private void getMenuItems(boolean isAdmin) {
        menuItems = Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(MenuItem.class) != null)
                .filter(method -> !method.getAnnotation(MenuItem.class).isAdmin() || isAdmin)  // 按权限过滤
                .collect(Collectors.toMap(
                        method -> method.getAnnotation(MenuItem.class).serial(),
                        Function.identity(),
                        (m1, m2) -> m1,
                        TreeMap::new));
    }

    private void printMenuItems() {
        printer.printMenuItems();
    }

    private void printRegisterSuccess(String userName) {
        String message = CREATION_SUCCESS_FORMATTER.format(new String[]{userName});
        out.println(message);
    }

    private void printAccountInfo(AccountInfo info) {
        String message = LOGIN_SUCCESS_FORMATTER.format(new String[]{
                info.getUserName(),
                info.getPhoneNumber(),
                info.getEMail()});
        out.println(message);
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
        manager.close();
        dbUtil.close();
    }

    public TreeMap<String, Method> getMenuItems() {
        return menuItems;
    }

    public PrintStream getOut() {
        return out;
    }

    public static class Printer {
        private final Console console;

        public Printer(Console console) {
            this.console = console;
        }

        public void printMenuItems() {
            console.getOut().println();
            console.getMenuItems().forEach((key, value) ->
                    console.getOut().printf("%s: %s\n",
                            key,
                            value.getAnnotation(MenuItem.class).name()));
            console.getOut().println("请输入你的选择：");
        }
    }
}
