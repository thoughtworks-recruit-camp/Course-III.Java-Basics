package com.thoughtworks.account.security;

import com.thoughtworks.account.EncryptedPassword;
import com.thoughtworks.account.LoginData;
import com.thoughtworks.account.errors.InvalidField;
import com.thoughtworks.account.errors.InvalidFormat;
import com.thoughtworks.account.errors.MaxTriesExceeded;
import com.thoughtworks.account.errors.UserNotFound;
import com.thoughtworks.account.Cypher;
import com.thoughtworks.account.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Deprecated
class SecurityHandlerTest {

    public static final String SQL_GET_RETRIES_LEFT = SecurityHandler.SQL_GET_RETRIES_LEFT;
    public static final String SQL_GET_ENCRYPTED_PASSWORD = SecurityHandler.SQL_GET_ENCRYPTED_PASSWORD;
    public static final String SQL_REDUCE_RETRIES_LEFT = SecurityHandler.SQL_REDUCE_RETRIES_LEFT;
    private ResultSet mockRetriesResult;
    private ResultSet mockPasswordResult;
    private SecurityHandler securityHandler = new SecurityHandler();

    LoginData loginFields;

    SecurityHandlerTest() throws InvalidFormat, InvalidField {
        loginFields = Utils.parseLoginString("abc,1234567a");
    }

    @BeforeEach
    void setUp() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockRetriesStatement = mock(PreparedStatement.class);
        PreparedStatement mockPasswordStatement = mock(PreparedStatement.class);
        PreparedStatement mockReduceStatement = mock(PreparedStatement.class);
        mockRetriesResult = mock((ResultSet.class));
        mockPasswordResult = mock((ResultSet.class));
        when(mockConnection.prepareStatement(SQL_GET_RETRIES_LEFT)).thenReturn(mockRetriesStatement);
        when(mockRetriesStatement.executeQuery()).thenReturn(mockRetriesResult);
        when(mockConnection.prepareStatement(SQL_GET_ENCRYPTED_PASSWORD)).thenReturn(mockPasswordStatement);
        when(mockPasswordStatement.executeQuery()).thenReturn(mockPasswordResult);
        when(mockConnection.prepareStatement(SQL_REDUCE_RETRIES_LEFT)).thenReturn(mockReduceStatement);

        securityHandler.setConnection(mockConnection);
    }

    @Test
    void should_success_verify_password_when_retires_left_is_3_and_password_correct() throws SQLException, UserNotFound, MaxTriesExceeded {
        when(mockRetriesResult.next()).thenReturn(true);
        when(mockRetriesResult.getInt(1)).thenReturn(3); // retriesLeft

        EncryptedPassword password = Cypher.encryptRawPassword("1234567a");
        when(mockPasswordResult.getBytes(1)).thenReturn(password.getDigest());
        when(mockPasswordResult.getBytes(2)).thenReturn(password.getSalt());

        assertTrue(securityHandler.isValidLogin(loginFields));
    }

    @Test
    void should_fail_verify_password_when_retires_left_is_3_and_password_incorrect() throws SQLException, UserNotFound, MaxTriesExceeded, InvalidFormat, InvalidField {

        when(mockRetriesResult.next()).thenReturn(true);
        when(mockRetriesResult.getInt(1)).thenReturn(3); // retriesLeft

        EncryptedPassword password = Cypher.encryptRawPassword("1234567a");
        when(mockPasswordResult.getBytes(1)).thenReturn(password.getDigest());
        when(mockPasswordResult.getBytes(2)).thenReturn(password.getSalt());

        LoginData wrongLoginFields = Utils.parseLoginString("abc,1234567b");
        assertFalse(securityHandler.isValidLogin(wrongLoginFields));
    }

    @Test
    void should_throw_user_not_found_when_result_set_is_empty() throws SQLException {
        when(mockRetriesResult.next()).thenReturn(false);

        assertThrows(UserNotFound.class, () -> securityHandler.isValidLogin(loginFields));
    }

    @Test
    void should_throw_max_retries_exceeded_when_retries_is_0() throws SQLException {
        when(mockRetriesResult.next()).thenReturn(true);
        when(mockRetriesResult.getInt(1)).thenReturn(0); // retriesLeft

        assertThrows(MaxTriesExceeded.class, () -> securityHandler.isValidLogin(loginFields));
    }

}