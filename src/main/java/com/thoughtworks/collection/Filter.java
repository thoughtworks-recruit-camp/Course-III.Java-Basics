package com.thoughtworks.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Filter {

    List<Integer> array;

    public Filter(List<Integer> array) {
        this.array = array;
    }

    public List<Integer> filterEven() {
        return array.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
    }

    public List<Integer> filterMultipleOfThree() {
        return array.stream()
                .filter(x -> x % 3 == 0)
                .collect(Collectors.toList());

    }

    public List<Integer> getCommonElements(List<Integer> firstList, List<Integer> secondList) {
        return firstList.stream()
                .filter(new HashSet<>(secondList)::contains)
                .collect(Collectors.toList());
    }

    public List<Integer> getCommonElementsAlt(List<Integer> firstList, List<Integer> secondList) {
        Set<Integer> firstSet = new LinkedHashSet<>(firstList);
        firstSet.retainAll(secondList);
        return new ArrayList<>(firstSet);
    }

    public List<Integer> getDifferentElements() {
        return array.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
