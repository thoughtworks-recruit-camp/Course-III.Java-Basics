package com.thoughtworks.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practice1 {
    public static void main(String[] args) {
        System.out.println(getUniqueRandomIntegers(0, 20, 10));
    }

    public static Collection<Integer> getUniqueRandomIntegers(int min, int max, int count) {
        List<Integer> ints = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
        Collections.shuffle(ints);
        return ints.subList(0, count);
    }
}
