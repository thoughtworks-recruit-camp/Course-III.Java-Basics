package com.thoughtworks.account;

import com.thoughtworks.account.errors.WrongPassword;
import com.thoughtworks.account.errors.MaxTriesExceeded;
import com.thoughtworks.account.errors.UserAlreadyExists;
import com.thoughtworks.account.errors.UserNotFound;


import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public final class AccountManager implements AutoCloseable {
    private final AccountInfoRepository infoRepository;
    private final AccountSecurityRepository securityRepository;
    public static final int MAX_TRIES = 3;

    public AccountManager() {
        this.infoRepository = new AccountInfoRepository();
        this.securityRepository = new AccountSecurityRepository();
    }

    public void setConnection(Connection connection) {
        setInfoRepoConnection(connection);
        setSecurityRepoConnection(connection);
    }

    public void setSecurityRepoConnection(Connection connection) {
        securityRepository.setConnection(connection);
    }

    public void setInfoRepoConnection(Connection connection) {
        infoRepository.setConnection(connection);
    }

    public void createNewAccount(RegisterData registerFields) throws SQLException, UserAlreadyExists {
        String userName = registerFields.getUserName();
        if (securityRepository.queryByPK(userName).isPresent()) {
            throw new UserAlreadyExists(userName);
        }
        AccountInfo newAccountInfo = Utils.createInfo(registerFields);
        AccountSecurity newAccountSecurity = Utils.createSecurity(registerFields, MAX_TRIES);
        infoRepository.save(newAccountInfo);
        securityRepository.save(newAccountSecurity);
    }

    public Optional<AccountInfo> getAccountInfo(LoginData loginFields) throws SQLException, UserNotFound, MaxTriesExceeded, WrongPassword {
        if (!isVerifiedLogin(loginFields)) {
            throw new WrongPassword();
        }
        return infoRepository.queryByPKExclusive(loginFields.getUserName());
    }

    public void resetTriesLeft(String userName) throws UserNotFound, SQLException {
        securityRepository.setTriesLeft(userName, MAX_TRIES);
    }

    // 使用事务进行查询剩余次数->验证密码->更新剩余次数，加行锁防止多个进程同时尝试验证密码
    private boolean isVerifiedLogin(LoginData loginFields) throws SQLException, UserNotFound, MaxTriesExceeded {
        securityRepository.beginTransaction();
        boolean isMatch;
        try {
            String userName = loginFields.getUserName();
            // 首先以独占模式验证剩余尝试次数
            AccountSecurity security = securityRepository.queryByPKExclusive(userName)
                    .orElseThrow(() -> new UserNotFound(userName));
            if (security.getTriesLeft() <= 0) {
                throw new MaxTriesExceeded(MAX_TRIES);
            }
            // 若用户存在且剩余查询次数>0，则查询密码作比较
            byte[] salt = security.getPasswordSalt();
            EncryptedPassword storedPassword = new EncryptedPassword(security.getPasswordDigest(), salt);
            EncryptedPassword loginPassword = Cypher.encryptRawPassword(loginFields.getRawPassword(), salt);
            isMatch = Objects.equals(storedPassword, loginPassword);
            // 密码正确时尝试次数重置；密码错误时将尝试次数减一
            if (isMatch) {
                security.setTriesLeft(MAX_TRIES);
            } else {
                security.setTriesLeft(security.getTriesLeft() - 1);
            }
            securityRepository.updateByPK(security.getUserName(), security);
            return isMatch;
        } finally {
            securityRepository.endTransaction();
        }
    }

    @Override
    public void close() {
        infoRepository.close();
        securityRepository.close();
    }
}
