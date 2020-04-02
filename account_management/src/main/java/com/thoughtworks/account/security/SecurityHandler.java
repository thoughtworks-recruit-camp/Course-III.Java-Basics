package com.thoughtworks.account.security;

import com.thoughtworks.account.EncryptedPassword;
import com.thoughtworks.account.LoginData;
import com.thoughtworks.account.errors.MaxTriesExceeded;
import com.thoughtworks.account.errors.UserNotFound;
import com.thoughtworks.account.Cypher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Deprecated
public class SecurityHandler {

    public static final String SQL_GET_RETRIES_LEFT = "SELECT retries_left FROM account_status WHERE user_name = ? FOR UPDATE";
    public static final String SQL_GET_ENCRYPTED_PASSWORD = "SELECT password_digest, password_salt FROM account_security WHERE user_name = ?";
    public static final String SQL_REDUCE_RETRIES_LEFT = "UPDATE account_status SET retries_left = IF(retries_left < 1, 0, retries_left - 1) WHERE user_name = ?";
    private static final int MAX_RETRIES = 3;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;


    // 使用事务进行查询剩余次数->验证密码->更新剩余次数，加行锁防止多个进程同时尝试验证密码
    public boolean isValidLogin(LoginData loginFields) throws SQLException, MaxTriesExceeded, UserNotFound {
        connection.setAutoCommit(false);
        boolean match;
        // 首先以独占模式查询剩余尝试次数
        try (PreparedStatement retriesStmt = connection.prepareStatement(SQL_GET_RETRIES_LEFT)) {
            retriesStmt.setString(1, loginFields.getUserName());
            try (ResultSet retriesResult = retriesStmt.executeQuery()) {
                if (!retriesResult.next()) {
                    throw new UserNotFound(loginFields.getUserName());
                }
                if (retriesResult.getInt(1) <= 0) {
                    throw new MaxTriesExceeded(MAX_RETRIES);
                }
            }
            // 若用户存在且剩余查询次数>0，则查询密码作比较
            try (PreparedStatement passwordStatement = connection.prepareStatement(SQL_GET_ENCRYPTED_PASSWORD)) {
                passwordStatement.setString(1, loginFields.getUserName());
                try (ResultSet passwordResult = passwordStatement.executeQuery()) {
                    match = isMatch(loginFields, passwordResult);
                    if (!match) {
                        // 密码错误时将尝试次数减一
                        try (PreparedStatement reduceStatement = connection.prepareStatement(SQL_REDUCE_RETRIES_LEFT)) {
                            reduceStatement.setString(1, loginFields.getUserName());
                            reduceStatement.executeUpdate();
                        }
                    }
                    return match;
                }
            }
        } finally {
            connection.commit();
        }
    }

    public static boolean isMatch(LoginData loginFields, ResultSet resultSet) throws SQLException {
        boolean match;
        resultSet.next();
        byte[] storedDigest = resultSet.getBytes(1);
        byte[] storedSalt = resultSet.getBytes(2);
        EncryptedPassword storedPassword = new EncryptedPassword(storedDigest, storedSalt);
        EncryptedPassword loginPassword = Cypher.encryptRawPassword(loginFields.getRawPassword(), storedSalt);
        match = Objects.equals(storedPassword, loginPassword);
        return match;
    }
}
