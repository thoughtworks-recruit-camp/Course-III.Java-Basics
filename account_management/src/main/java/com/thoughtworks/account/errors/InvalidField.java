package com.thoughtworks.account.errors;

public class InvalidField extends Exception {
    private final String fieldName;

    public InvalidField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getMessage() {
        return String.format("%s不合法", getFieldName());
    }
}
