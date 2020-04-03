package com.thoughtworks.account;

import com.thoughtworks.account.exceptions.InvalidField;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.thoughtworks.account.Regex.RawPasswordRegex;
import static com.thoughtworks.account.Regex.UserNameRegex;
import static org.junit.jupiter.api.Assertions.*;

class RegexTest {
    @Test
    void should_limit_user_name_length_between_2_and_12() {
        List<String> validUserNames = Arrays.asList(
                "22", "88888888", "121212121212");
        validUserNames.forEach(userName ->
                assertTrue(UserNameRegex.match(userName)));

        List<String> invalidUserNames = Arrays.asList(
                "1", "14141414141414");
        invalidUserNames.forEach(userName ->
                assertThrows(InvalidField.class, () -> UserNameRegex.validate(userName)));
    }

    @Test
    void should_only_use_words_and_letters_in_user_name_() {
        List<String> validUserNames = Arrays.asList(
                "USER", "user", "user1", "100user");
        validUserNames.forEach(userName ->
                assertTrue(UserNameRegex.match(userName)));

        List<String> invalidUserNames = Arrays.asList(
                "_user", "user-", "@user", "us[er", "\\user", "123!user");
        invalidUserNames.forEach(userName ->
                assertThrows(InvalidField.class, () -> UserNameRegex.validate(userName)));
    }

    @Test
    void should_use_at_least_one_digit_and_one_letter_in_password() {
        List<String> validRawPasswords = Arrays.asList(
                "1234567a", "abcdefg1");
        validRawPasswords.forEach(rawPassword ->
                assertTrue(RawPasswordRegex.match(rawPassword)));

        List<String> invalidRawPasswords = Arrays.asList(
                "12345678", "abcdefgh");
        invalidRawPasswords.forEach(rawPassword ->
                assertThrows(InvalidField.class, () -> RawPasswordRegex.validate(rawPassword)));
    }
}