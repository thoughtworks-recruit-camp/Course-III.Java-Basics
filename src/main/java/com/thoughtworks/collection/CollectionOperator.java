package com.thoughtworks.collection;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionOperator {
    public List<Integer> getListByInterval(int left, int right) {
        int step = Math.round(Math.signum(right - left));
        int endInclusive = Math.abs(right - left);
        return IntStream.rangeClosed(0, endInclusive)
                .map(x -> left + step * x)
                .boxed().collect(Collectors.toList());
    }

    public List<Integer> getEvenListByIntervals(int left, int right) {
        int step = Math.round(Math.signum(right - left));
        int endInclusive = Math.abs(right - left);
        return IntStream.rangeClosed(0, endInclusive)
                .map(x -> left + step * x)
                .filter(x -> x % 2 == 0)
                .boxed().collect(Collectors.toList());
    }

    public List<Integer> popEvenElements(int[] array) {
        return Arrays.stream(array)
                .filter(x -> x % 2 == 0)
                .boxed().collect(Collectors.toList());
    }

    public int popLastElement(int[] array) {
        return array[array.length - 1];
    }

    public List<Integer> popCommonElement(int[] firstArray, int[] secondArray) {
        Set<Integer> firstSet = Arrays.stream(firstArray)
                .boxed().collect(Collectors.toCollection(LinkedHashSet::new));
        return Arrays.stream(secondArray).filter(firstSet::contains).boxed().collect(Collectors.toList());
    }

    public List<Integer> popCommonElementAlt(int[] firstArray, int[] secondArray) {
        Set<Integer> firstSet = Arrays.stream(firstArray)
                .boxed().collect(Collectors.toCollection(LinkedHashSet::new));
        firstSet.retainAll(Arrays.stream(secondArray)
                .boxed().collect(Collectors.toList()));
        return new ArrayList<>(firstSet);
    }

    public List<Integer> addUncommonElement(Integer[] firstArray, Integer[] secondArray) {
        return Stream.concat(Arrays.stream(firstArray), Arrays.stream(secondArray)).distinct().collect(Collectors.toList());
    }

    public List<Integer> addUncommonElementAlt(Integer[] firstArray, Integer[] secondArray) {
        Set<Integer> firstSet = Arrays.stream(firstArray)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        firstSet.addAll(Arrays.asList(secondArray));
        return new ArrayList<>(firstSet);
    }
}
