package com.thoughtworks.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InverseReduce {

    private Random random;

    public InverseReduce(Random random) {
        this.random = random;
    }

    public List<Integer> divideToSmaller(int number) {
        LinkedList<Integer> result = new LinkedList<>();
        Stream.iterate(number, n -> n - random.nextInt(3))
                .skip(1)
                .peek(result::add)
                .filter(x -> x < 0)
                .findFirst().ifPresent(x -> result.removeLast());
        return result;
    }

    public List<Integer> divideToSmallerAlt(int number) {
        return Stream.iterate(number, n -> n - random.nextInt(3))
                .skip(1)
                .takeWhile(x -> x > 0).collect(Collectors.toList());
    }
}
