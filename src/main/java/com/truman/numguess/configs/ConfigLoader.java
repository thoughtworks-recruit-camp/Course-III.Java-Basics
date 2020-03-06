package com.thoughtworks.numguess.configs;

public class ConfigLoader {
    public static Config loadDefaultConfig() {
        return new DefaultConfig();
    }

    public static Config loadBrokenConfig() {
        return new BrokenConfig();
    }

    @Deprecated
    public static Config loadCustomConfig() {
        return null;
    }
}