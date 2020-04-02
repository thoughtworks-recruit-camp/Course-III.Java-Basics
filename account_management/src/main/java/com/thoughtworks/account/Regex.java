package com.thoughtworks.account;

import com.thoughtworks.account.errors.InvalidField;

enum Regex {
    UserNameRegex("^[A-Za-z0-9]{2,12}$", "用户名"),
    PhoneNumberRegex("^1\\d{10}$", "手机号"),
    EMailRegex("^.+@.+$", "邮箱"),
    RawPasswordRegex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$", "密码");
    private final String pattern;
    private final String fieldName;

    Regex(String pattern, String name) {
        this.pattern = pattern;
        this.fieldName = name;
    }

    public boolean match(String string) {
        return string.matches(pattern);
    }

    public void validate(String string) throws InvalidField {
        if (!match(string)) {
            throw new InvalidField(fieldName);
        }
    }
}
