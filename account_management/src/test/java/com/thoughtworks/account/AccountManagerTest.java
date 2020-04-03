package com.thoughtworks.account;

import com.thoughtworks.repository.DbUtil;
import com.thoughtworks.account.errors.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static com.thoughtworks.account.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountManagerTest {
    private static AccountManager testManager = new AccountManager();
    private static DbUtil testConnector = new DbUtil(TestUtil.getTestConnParams());

    @BeforeAll
    static void beforeAll() throws InvalidFormat, SQLException, UserAlreadyExists, InvalidField {
        TestUtil.setUpTestDb();
        TestData.initData();

        testManager.setConnection(testConnector.getConnection());
    }

    @AfterAll
    static void afterAll() throws SQLException {
        testManager.setConnection(null);
        TestUtil.tearDownTestDb();
    }

    @Test
    @Order(1)
    void should_throw_user_already_exist() {
        assertThrows(UserAlreadyExists.class,
                () -> testManager.createNewAccount(registerDataA));
    }

    @Test
    @Order(2)
    void should_throw_user_not_found() {
        assertThrows(UserNotFound.class,
                () -> testManager.getAccountInfo(loginDataB));
    }

    @Test
    @Order(3)
    void should_success_verify() throws MaxTriesExceeded, SQLException, WrongPassword, UserNotFound {
        assertEquals(accountInfoA,
                testManager.getAccountInfo(loginDataA).get());
    }

    @Test
    @Order(4)
    void should_success_verify_after_creation() throws SQLException, UserAlreadyExists, WrongPassword, MaxTriesExceeded, UserNotFound {
        testManager.createNewAccount(registerDataB);
        assertEquals(accountInfoB,
                testManager.getAccountInfo(loginDataB).get());
    }

    @RepeatedTest(2)  // triesLeft: 3->1
    @Order(5)
    void should_throw_wrong_password() {
        assertThrows(WrongPassword.class,
                () -> testManager.getAccountInfo(loginDataBMod));
    }

    @Test
    @Order(6)
    void should_success_verify_with_tires_left_1() throws MaxTriesExceeded, SQLException, WrongPassword, UserNotFound {
        assertEquals(accountInfoB,
                testManager.getAccountInfo(loginDataB).get()); // triesLeft: 1->3
    }

    @RepeatedTest(2)  // triesLeft: 3->1
    @Order(7)
    void should_reset_tries_left_after_success_login() {
        assertThrows(WrongPassword.class,
                () -> testManager.getAccountInfo(loginDataBMod));
    }

    @Test
    @Order(8)
    void should_throw_wrong_password_and_then_max_tries_exceeded() {
        assertThrows(WrongPassword.class,
                () -> testManager.getAccountInfo(loginDataBMod));  // triesLeft: 1->0
        assertThrows(MaxTriesExceeded.class,
                () -> testManager.getAccountInfo(loginDataBMod));  // triesLeft: 0!
    }

    @Test
    @Order(9)
    void should_throw_max_tries_exceeded_with_correct_password() {
        assertThrows(MaxTriesExceeded.class,
                () -> testManager.getAccountInfo(loginDataB));  // triesLeft: 0!
    }
}
