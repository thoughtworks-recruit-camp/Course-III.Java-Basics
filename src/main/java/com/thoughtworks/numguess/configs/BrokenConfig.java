package com.thoughtworks.numguess.configs;

import java.io.File;

final class BrokenConfig implements Config {
    @Override
    public File getAnswerFile() {
        return new File("D:\\Users\\Truman\\Desktop\\dev\\java\\number_guess\\src\\main\\resources\\answer2.txt");
    }
}
