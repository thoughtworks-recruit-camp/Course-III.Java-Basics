package com.thoughtworks.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyMap {

    List<Integer> array;
    private static final int OFFSET = 96;
    private static final Function<Integer, String> numToLetter =
            n -> Character.toString(n + OFFSET);

    public MyMap(List<Integer> array) {
        this.array = array;
    }

    public List<Integer> getTriple() {
        return array.stream()
                .map(x -> x * 3)
                .collect(Collectors.toList());
    }

    public List<String> mapLetter() {
        return array.stream()
                .map(numToLetter)
                .collect(Collectors.toList());
    }

    public List<String> mapLetters() {
        return array.stream()
                .map(this::numberToLetters)
                .collect(Collectors.toList());
    }

    public List<Integer> sortFromBig() {
        return array.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Integer> sortFromSmall() {
        return array.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private String numberToLetters(int number) {
        List<Integer> digits = new ArrayList<>();
        int remain = number;

        while (remain > 26) {
            int digit = remain % 26 == 0 ? 26 : remain % 26;
            digits.add(digit);
            remain = (remain - digit) / 26;
        }
        digits.add(remain);

        Collections.reverse(digits);
        return digits.stream()
                .map(numToLetter)
                .collect(Collectors.joining());
    }
}
