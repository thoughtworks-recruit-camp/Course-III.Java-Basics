package com.thoughtworks.account;

import lombok.Data;

@Data
public final class LoginData implements StringData {
    @Validate(Regex.UserNameRegex)
    private String userName;
    @Validate(Regex.RawPasswordRegex)
    private String rawPassword;
}
