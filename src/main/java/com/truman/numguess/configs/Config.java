package com.thoughtworks.numguess.configs;

import java.io.File;

public interface Config {
    default int getTriesMax() {
        return 6;
    }

    default int getAnswerLen() {
        return 4;
    }

    default File getAnswerFile() {
        return new File("D:\\Users\\Truman\\Desktop\\dev\\java\\number_guess\\src\\main\\resources\\answer.txt");
    }
}
