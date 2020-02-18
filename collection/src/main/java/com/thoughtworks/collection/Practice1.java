package com.thoughtworks.collection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practice1 {
    public static void main(String[] args) {
        System.out.println(getUniqueRandomIntegers(0, 20, 10));
        System.out.println(getUniqueRandomIntegersAlt(0, 20, 10));

    }

    public static Collection<Integer> getUniqueRandomIntegers(int min, int max, int count) {
        List<Integer> intsList = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
        Collections.shuffle(intsList);
        return intsList.subList(0, count);
    }

    public static Collection<Integer> getUniqueRandomIntegersAlt(int min, int max, int count) {
        HashSet<Integer> intsSet = new HashSet<>();
        while (intsSet.size() < count) {
            intsSet.add(min + (int) (Math.random() * (max + 1)));
        }
        return new ArrayList<>(intsSet);
    }
}
