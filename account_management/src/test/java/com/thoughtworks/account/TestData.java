package com.thoughtworks.account;

import com.thoughtworks.account.exceptions.InvalidField;
import com.thoughtworks.account.exceptions.InvalidFormat;

public final class TestData {
    public static String registerInputA = "personA,13000000001,a@a,1234567a";
    public static String registerInputB = "personB,13000000002,b@b,1234567b";
    public static String loginInputA = "personA,1234567a";
    public static String loginInputB = "personB,1234567b";
    public static String loginInputBMod = "personB,1234567c";

    public static RegisterData registerDataA;
    public static RegisterData registerDataB;
    public static AccountInfo accountInfoA;
    public static AccountInfo accountInfoB;
    public static LoginData loginDataA;
    public static LoginData loginDataB;
    public static LoginData loginDataBMod;

    private TestData() {
    }

    public static void initData() throws InvalidFormat, InvalidField {
        registerDataA = Utils.parseInput(registerInputA, RegisterData.class);
        registerDataB = Utils.parseInput(registerInputB, RegisterData.class);
        accountInfoA = Utils.createInfo(registerDataA);
        accountInfoB = Utils.createInfo(registerDataB);
        loginDataA = Utils.parseInput(loginInputA, LoginData.class);
        loginDataB = Utils.parseInput(loginInputB, LoginData.class);
        loginDataBMod = Utils.parseInput(loginInputBMod, LoginData.class);
    }
}
