package com.thoughtworks.account;

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
}
