package com.thoughtworks.account;

import com.thoughtworks.repository.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AccountInfo {
    @PrimaryKey
    private String userName;
    private String phoneNumber;
    private String eMail;
}
