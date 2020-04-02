package com.thoughtworks.account;

import com.thoughtworks.repository.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AccountSecurity {
    @PrimaryKey
    private String userName;
    private byte[] passwordDigest;
    private byte[] passwordSalt;
    private Integer triesLeft;
}
