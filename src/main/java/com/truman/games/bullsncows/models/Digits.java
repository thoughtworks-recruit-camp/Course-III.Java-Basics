package com.truman.games.bullsncows.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Digits {
    private final List<Integer> digits;
    private final String literal;

    public Digits(Collection<Integer> digits) {
        this.digits = new ArrayList<>(digits);
        this.literal = digits.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public List<Integer> getDigits() {
        return digits;
    }

    public String getLiteral() {
        return literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
