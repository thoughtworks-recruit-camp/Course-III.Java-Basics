package com.thoughtworks.account;

import com.thoughtworks.account.errors.InvalidField;
import com.thoughtworks.account.errors.InvalidFormat;
import com.thoughtworks.account.errors.UserAlreadyExists;
import com.thoughtworks.repository.DbUtil;

import java.sql.SQLException;

public final class TestUtil {
    private TestUtil() {

    }

    private static DbUtil testDbUtil = new DbUtil(
            "jdbc:mysql://localhost:3306/account_management_test?serverTimezone=UTC",
            "root",
            "root");

    public static DbUtil getTestDbUtil() {
        return testDbUtil;
    }

    public static void setUpTestDb() throws SQLException, InvalidFormat, InvalidField, UserAlreadyExists {
        AccountManager testManager = new AccountManager();
        testManager.setConnection(testDbUtil.getConnection());
        clearTables();
        testManager.createNewAccount(
                Utils.parseInput("personA,13000000001,a@a,1234567a", RegisterData.class));
        testManager.setConnection(null);
        testDbUtil.close();
    }

    public static void tearDownTestDb() throws SQLException {
        clearTables();
        testDbUtil.close();
    }
    private static void clearTables() throws SQLException {
        testDbUtil.clearTable("account_info");
        testDbUtil.clearTable("account_security");
    }
}
