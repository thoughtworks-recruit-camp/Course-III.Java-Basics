package com.thoughtworks.account;

import com.thoughtworks.account.errors.InvalidField;
import com.thoughtworks.account.errors.InvalidFormat;
import com.thoughtworks.account.errors.UserAlreadyExists;
import com.thoughtworks.repository.ConnectionParams;
import com.thoughtworks.repository.DbUtil;

import java.sql.SQLException;

public final class TestUtil {
    private final static ConnectionParams CONN_PARAM = new ConnectionParams("jdbc:mysql://localhost:3306/account_management_test?serverTimezone=UTC", "root", "root");
    private static DbUtil dbUtil = new DbUtil(CONN_PARAM);

    private TestUtil() {
    }

    public static ConnectionParams getTestConnParams() {
        return CONN_PARAM;
    }

    public static void setUpTestDb() throws SQLException, InvalidFormat, InvalidField, UserAlreadyExists {
        AccountManager testManager = new AccountManager();
        testManager.setConnection(dbUtil.getConnection());
        clearTables();
        testManager.createNewAccount(
                Utils.parseInput("personA,13000000001,a@a,1234567a", RegisterData.class));
        testManager.setConnection(null);
    }

    public static void tearDownTestDb() throws SQLException {
        clearTables();
        dbUtil.close();
    }

    private static void clearTables() throws SQLException {
        dbUtil.clearTable("account_info");
        dbUtil.clearTable("account_security");
    }
}
