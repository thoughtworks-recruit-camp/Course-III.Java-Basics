package com.thoughtworks.account;

import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class Cypher {
    private static final int SALT_BITS = 32;
    private static final String ALGORITHM = "SHA-256";
    private static SecureRandom random = new SecureRandom();

    private Cypher() {
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    public static EncryptedPassword encryptRawPassword(String rawPassword, byte[] salt) {
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        digest.update(rawPassword.getBytes());
        byte[] passDigest = digest.digest(salt);
        return new EncryptedPassword(passDigest, salt);
    }

    public static EncryptedPassword encryptRawPassword(String rawPassword) {
        byte[] salt = new byte[SALT_BITS];
        random.nextBytes(salt);
        return encryptRawPassword(rawPassword, salt);
    }
}
