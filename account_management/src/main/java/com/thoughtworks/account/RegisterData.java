package com.thoughtworks.account;

import lombok.Data;

@Data
public class RegisterData implements StringData {
    @Validate(Regex.UserNameRegex)
    private String userName;
    @Validate(Regex.PhoneNumberRegex)
    private String phoneNumber;
    @Validate(Regex.EMailRegex)
    private String eMail;
    @Validate(Regex.RawPasswordRegex)
    private String rawPassword;
}
