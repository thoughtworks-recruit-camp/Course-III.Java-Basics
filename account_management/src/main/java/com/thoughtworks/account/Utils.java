package com.thoughtworks.account;

import com.thoughtworks.account.errors.InvalidFormat;
import com.thoughtworks.account.errors.InvalidField;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Optional;

public final class Utils {
    private Utils() {
    }

    @SneakyThrows({NoSuchMethodException.class, InvocationTargetException.class, IllegalAccessException.class, InstantiationException.class})

    // 解析后先验证，再set，在同一个循环内进行操作
    public static <SD extends StringData> SD parseInput(String input, Class<SD> dataType) throws InvalidFormat, InvalidField {
        String[] inputs = input.split(",");
        Field[] fields = dataType.getDeclaredFields();
        String inputPattern = getInputPattern(fields.length);
        if (!input.matches(inputPattern)) {
            throw new InvalidFormat();
        }
        SD data = dataType.getConstructor().newInstance();
        for (int i = 0; i < fields.length; i++) {
            Optional<Regex> regex = Optional.ofNullable(fields[i].getAnnotation(Validate.class)).map(Validate::value);
            if (regex.isPresent()) {
                regex.get().validate(inputs[i]);
            }
            dataType.getMethod(getAccessorName("set", fields[i].getName()), String.class)
                    .invoke(data, inputs[i]);
        }
        return data;
    }

    public static AccountInfo createInfo(RegisterData fields) {
        return new AccountInfo(fields.getUserName(), fields.getPhoneNumber(), fields.getEMail());
    }

    public static AccountSecurity createSecurity(RegisterData fields, int maxTries) {
        EncryptedPassword password = Cypher.encryptRawPassword(fields.getRawPassword());
        return new AccountSecurity(
                fields.getUserName(),
                password.getDigest(),
                password.getSalt(),
                maxTries
        );
    }

    // 暂时未使用，已经整合进parseInput中；留作备用
    @SneakyThrows({NoSuchMethodException.class, InvocationTargetException.class, IllegalAccessException.class})
    private static void validate(StringData unvalidatedFields) throws InvalidField {
        Class<? extends StringData> fieldsClass = unvalidatedFields.getClass();
        for (Field field : fieldsClass.getDeclaredFields()) {
            Method getter = fieldsClass.getMethod(getAccessorName("get", field.getName()));
            Optional<Regex> regex = Optional.ofNullable(field.getAnnotation(Validate.class)).map(Validate::value);
            if (regex.isPresent()) {
                regex.get().validate((String) getter.invoke(unvalidatedFields));
            }
        }
    }

    private static String getInputPattern(int length) {
        return String.format("^%s$",
                String.join(",",
                        Collections.nCopies(length, "[^,]+")));
    }

    private static String getAccessorName(String getOrSet, String fieldName) {
        return getOrSet + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    @Deprecated
    public static RegisterData parseRegisterString(String registerString) throws InvalidFormat, InvalidField {
        String inputPattern = "^[^,]+,[^,]+,[^,]+,[^,]+$";
        if (!registerString.matches(inputPattern)) {
            throw new InvalidFormat();
        }
        String[] inputFields = registerString.split(",");
        RegisterData registerFields = new RegisterData();
        registerFields.setUserName(inputFields[0]);
        registerFields.setPhoneNumber(inputFields[1]);
        registerFields.setEMail(inputFields[2]);
        registerFields.setRawPassword(inputFields[3]);
        validate(registerFields);
        return registerFields;
    }

    @Deprecated
    public static LoginData parseLoginString(String loginString) throws InvalidFormat, InvalidField {
        String inputPattern = "^[^,]+,[^,]+$";
        if (!loginString.matches(inputPattern)) {
            throw new InvalidFormat();
        }
        String[] inputFields = loginString.split(",");
        LoginData loginFields = new LoginData();
        loginFields.setUserName(inputFields[0]);
        loginFields.setRawPassword(inputFields[1]);
        validate(loginFields);
        return loginFields;
    }

    @Deprecated
    public static void validateRegisterFields(RegisterData registerFields) throws InvalidField {
        Regex.UserNameRegex.validate(registerFields.getUserName());
        Regex.PhoneNumberRegex.validate(registerFields.getPhoneNumber());
        Regex.EMailRegex.validate(registerFields.getEMail());
        Regex.RawPasswordRegex.validate(registerFields.getRawPassword());
    }

    @Deprecated
    public static void validateLoginFields(LoginData loginFields) throws InvalidField {
        Regex.UserNameRegex.validate(loginFields.getUserName());
        Regex.RawPasswordRegex.validate(loginFields.getRawPassword());
    }
}