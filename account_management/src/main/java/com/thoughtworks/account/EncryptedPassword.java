package com.thoughtworks.account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public final class EncryptedPassword {
    @Getter
    private final byte[] digest;
    @Getter
    private final byte[] salt;
}
