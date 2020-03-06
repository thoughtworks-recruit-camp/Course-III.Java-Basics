package com.thoughtworks.numguess;

import com.thoughtworks.numguess.exceptions.InvalidFormat;

final class Guess extends Answer {
    Guess(String input, int len) throws InvalidFormat {
        literal = input;
        digits = Validator.toDigits(literal, len);
    }
}

