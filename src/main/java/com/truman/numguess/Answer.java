package com.thoughtworks.numguess;

import java.util.List;

abstract class Answer {
    List<Integer> digits;
    String literal;

    List<Integer> getDigits() {
        return digits;
    }

    String getLiteral() {
        return literal;
    }
}
