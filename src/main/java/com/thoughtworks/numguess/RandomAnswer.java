package com.thoughtworks.numguess;

import java.util.LinkedList;
import java.util.Random;

import java.util.stream.Collectors;

final class RandomAnswer extends Answer {
     RandomAnswer(int digitsCount) {
        digits = new Random().ints(0, 10).boxed().distinct().limit(digitsCount).collect(Collectors.toCollection(LinkedList::new));
        literal = digits.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
