package com.thoughtworks.account.controller;

import com.thoughtworks.account.TestData;
import com.thoughtworks.account.TestUtil;
import com.thoughtworks.account.errors.ExitEvent;
import com.thoughtworks.account.errors.InvalidField;
import com.thoughtworks.account.errors.InvalidFormat;
import com.thoughtworks.account.errors.UserAlreadyExists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static com.thoughtworks.account.TestData.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsoleTest {
    private static ByteArrayOutputStream allOutput = new ByteArrayOutputStream(102400);

    private ByteArrayOutputStream tempOutput = new ByteArrayOutputStream(10240);
    private Iterator<String> mockIn;
    private Console testController;

    @BeforeAll
    static void beforeAll() throws SQLException, InvalidFormat, InvalidField, UserAlreadyExists {
        TestUtil.setUpTestDb();
        TestData.initData();
    }

    @AfterAll
    static void afterAll() throws SQLException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ConsoleTest.log"))) {
            writer.write(String.valueOf(allOutput));
        }
        TestUtil.tearDownTestDb();
    }

    @BeforeEach
    void setUp() throws SQLException {
        mockIn = mock(EmptyStringIterator.class);
        PrintStream testPrint = new PrintStream(tempOutput);
        TestScannerFilter testScannerWrapper = new TestScannerFilter(mockIn, testPrint);
        testController = new Console(testScannerWrapper, testPrint,TestUtil.getTestConnParams());
        testController.initConnection();
    }

    @AfterEach
    void tearDown() throws IOException {
        tempOutput.writeTo(allOutput);
        tempOutput.reset();
        String SplitLine = String.join("", Collections.nCopies(80, "-")) + "\n";
        allOutput.write(SplitLine.getBytes());
    }

    @Test
    @Order(1)
    void should_exit_in_main_menu() {
        when(mockIn.next()).thenReturn("3").thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertThrows(ExitEvent.class, () -> testController.run());
    }

    @Test
    @Order(2)
    void should_exit_in_sub_menus() {
        when(mockIn.next()).thenReturn("1").thenReturn("EXIT")
                .thenReturn("2").thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertThrows(ExitEvent.class, () -> testController.run());
    }

    @Test
    @Order(3)
    void should_prompt_wrong_option() {
        List<String> expectedOutputs = asList("输入错误，请重新选择:");

        when(mockIn.next()).thenReturn("4")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(4)
    void should_prompt_invalid_register_format() {
        List<String> expectedOutputs = asList("格式错误", "请按正确格式输入注册信息：");

        when(mockIn.next()).thenReturn("1")
                .thenReturn("1,2,3,4,")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));

        when(mockIn.next()).thenReturn("1")
                .thenReturn(",2,3,4,5")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(5)
    void should_prompt_invalid_register_field_user_name() {
        List<String> expectedOutputs = asList("用户名不合法", "请输入合法的注册信息：");

        when(mockIn.next()).thenReturn("1")
                .thenReturn("1,2,3,4")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(6)
    void should_prompt_invalid_register_field_phone_number() {
        List<String> expectedOutputs = asList("手机号不合法", "请输入合法的注册信息：");

        when(mockIn.next()).thenReturn("1")
                .thenReturn("12,2,3,4")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(7)
    void should_prompt_invalid_register_field_email() {
        List<String> expectedOutputs = asList("邮箱不合法", "请输入合法的注册信息：");

        when(mockIn.next()).thenReturn("1")
                .thenReturn("12,13012345678,3,4")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(8)
    void should_prompt_invalid_register_field_raw_password() {
        List<String> expectedOutputs = asList("密码不合法", "请输入合法的注册信息：");

        when(mockIn.next()).thenReturn("1")
                .thenReturn("12,13012345678,a@b,12345678")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(9)
    void should_prompt_user_already_exists() {
        List<String> expectedOutputs = asList("用户名已被使用：personA", "请更换用户名并重新输入注册信息：");

        when(mockIn.next()).thenReturn("1")
                .thenReturn(registerInputA)
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(10)
    void should_prompt_invalid_login_format() {
        List<String> expectedOutputs = asList("格式错误", "请按正确格式输入用户名和密码：");

        when(mockIn.next()).thenReturn("2")
                .thenReturn("1,2,")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));

        when(mockIn.next()).thenReturn("2")
                .thenReturn(",1,2")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(11)
    void should_prompt_invalid_login_field_user_name() {
        List<String> expectedOutputs = asList("用户名不合法", "请输入合法的登录信息：");

        when(mockIn.next()).thenReturn("2")
                .thenReturn("1,2")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(12)
    void should_prompt_invalid_login_field_raw_password() {
        List<String> expectedOutputs = asList("密码不合法", "请输入合法的登录信息：");

        when(mockIn.next()).thenReturn("2")
                .thenReturn("12,2")
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(13)
    void should_prompt_wrong_user_name_or_password_before_register() {
        List<String> expectedOutputs = asList("密码或用户名错误", "请重新输入用户名和密码：");

        when(mockIn.next()).thenReturn("2")
                .thenReturn(loginInputB)
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(14)
    void should_success_register() {
        List<String> expectedOutputs = asList("personB, 恭喜你注册成功！");

        when(mockIn.next()).thenReturn("1")
                .thenReturn(registerInputB)
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(15)
    void should_success_login_and_show_info_after_success_register() {
        List<String> expectedOutputs = asList("personB，欢迎回来！", "您的手机号是13000000002，邮箱是b@b");

        when(mockIn.next()).thenReturn("2")
                .thenReturn(loginInputB)
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @RepeatedTest(3)
    @Order(16)
    void should_prompt_wrong_user_name_or_password_with_wrong_password() {
        List<String> expectedOutputs = asList("密码或用户名错误", "请重新输入用户名和密码：");

        when(mockIn.next()).thenReturn("2")
                .thenReturn(loginInputBMod)
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @Test
    @Order(17)
    void should_prompt_max_tries_exceeded_with_any_password() {
        List<String> expectedOutputs = asList("您已3次输错密码，账号被锁定");

        when(mockIn.next()).thenReturn("2")
                .thenReturn(loginInputBMod)
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));

        when(mockIn.next()).thenReturn("2")
                .thenReturn(loginInputB)
                .thenReturn("EXIT");
        assertThrows(ExitEvent.class, () -> testController.run());
        assertTrue(hasExpectOutputs(expectedOutputs));
    }

    @SneakyThrows(IOException.class)
    private boolean hasExpectOutputs(List<String> expectedOutputs) {
        boolean result = new BufferedReader(
                new StringReader(tempOutput.toString()))
                .lines()
                .collect(Collectors.toSet())
                .containsAll(expectedOutputs);
        tempOutput.writeTo(allOutput);
        tempOutput.reset();
        return result;
    }
}
