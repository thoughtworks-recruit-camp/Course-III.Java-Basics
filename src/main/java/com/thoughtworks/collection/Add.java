package com.thoughtworks.collection;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Add {
    public int getSumOfEvens(int leftBorder, int rightBorder) {
        int max = Math.max(leftBorder, rightBorder);
        int min = Math.min(leftBorder, rightBorder);
        return IntStream.rangeClosed(min, max)
                .filter(x -> x % 2 == 0)
                .sum();
    }

    public int getSumOfOdds(int leftBorder, int rightBorder) {
        int max = Math.max(leftBorder, rightBorder);
        int min = Math.min(leftBorder, rightBorder);
        return IntStream.rangeClosed(min, max)
                .filter(x -> x % 2 != 0)
                .sum();
    }

    public int getSumTripleAndAddTwo(List<Integer> arrayList) {
        return arrayList.stream()
                .mapToInt(x -> x * 3 + 2)
                .sum();
    }

    public List<Integer> getTripleOfOddAndAddTwo(List<Integer> arrayList) {
        return arrayList.stream()
                .map(x -> x % 2 == 0 ? x : x * 3 + 2)
                .collect(Collectors.toList());
    }

    public int getSumOfProcessedOdds(List<Integer> arrayList) {
        return arrayList.stream()
                .filter(x -> x % 2 != 0)
                .mapToInt(x -> x * 3 + 5)
                .sum();
    }

    public List<Integer> getProcessedList(List<Integer> arrayList) {
        return IntStream.range(0, arrayList.size() - 1)
                .map(i -> (arrayList.get(i) + arrayList.get(i + 1)) * 3)
                .boxed()
                .collect(Collectors.toList());
    }

    public double getMedianOfEvenIndex(List<Integer> arrayList) {
        List<Integer> sortedEvensList = arrayList.stream()
                .filter(x -> x % 2 == 0)
                .sorted().collect(Collectors.toList());
        int len = sortedEvensList.size();
        return len % 2 == 0
                ? (double) (sortedEvensList.get(len / 2 - 1) + sortedEvensList.get(len / 2)) / 2
                : (double) sortedEvensList.get((len - 1) / 2);
    }

    public double getAverageOfEvenIndex(List<Integer> arrayList) {
        List<Integer> sortedEvensList = arrayList.stream()
                .filter(x -> x % 2 == 0)
                .sorted().collect(Collectors.toList());
        return (double) sortedEvensList.stream()
                .mapToInt(Integer::intValue)
                .sum() / sortedEvensList.size();
    }

    public boolean isIncludedInEvenIndex(List<Integer> arrayList, Integer specialElment) {
        return arrayList.stream().filter(x1 -> x1 % 2 == 0)
                .anyMatch(x -> x.equals(specialElment));
    }

    public List<Integer> getUnrepeatedFromEvenIndex(List<Integer> arrayList) {
        return arrayList.stream().filter(x -> x % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Integer> sortByEvenAndOdd(List<Integer> arrayList) {
        Map<Boolean, List<Integer>> evensAndOdds = arrayList.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        return Stream.concat(
                evensAndOdds.get(true).stream().sorted(),
                evensAndOdds.get(false).stream().sorted(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
