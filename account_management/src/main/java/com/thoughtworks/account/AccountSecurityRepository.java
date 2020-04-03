package com.thoughtworks.account;

import com.thoughtworks.account.errors.UserNotFound;
import com.thoughtworks.repository.BasicRepository;

import java.sql.SQLException;

final class AccountSecurityRepository extends BasicRepository<AccountSecurity> {
    void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    void setTriesLeft(String userName, int triesLeft) throws SQLException, UserNotFound {
        AccountSecurity accountSecurity = queryByPK(userName).orElseThrow(() -> new UserNotFound(userName));
        accountSecurity.setTriesLeft(triesLeft);
        updateByPK(userName, accountSecurity);
    }
}
