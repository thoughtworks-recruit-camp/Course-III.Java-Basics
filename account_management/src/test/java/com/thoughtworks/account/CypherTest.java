package com.thoughtworks.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CypherTest {
    @Test
    void should_generate_same_digest_with_same_salt_and_recognize_as_same_password() {
        String rawPassword = "password";
        EncryptedPassword password1 = Cypher.encryptRawPassword(rawPassword);
        byte[] salt = password1.getSalt();
        EncryptedPassword password2 = Cypher.encryptRawPassword(rawPassword, salt);

        assertArrayEquals(password1.getDigest(), password2.getDigest());
        assertEquals(password1, password2);
    }

    @Test
    void should_generate_different_digest_with_random_salt_and_recognize_as_different_password() {
        String rawPassword = "password";
        EncryptedPassword password1 = Cypher.encryptRawPassword(rawPassword);
        EncryptedPassword password2 = Cypher.encryptRawPassword(rawPassword);

        assertFalse(Arrays.equals(password1.getDigest(), password2.getDigest()));
        assertNotEquals(password1, password2);
    }
}