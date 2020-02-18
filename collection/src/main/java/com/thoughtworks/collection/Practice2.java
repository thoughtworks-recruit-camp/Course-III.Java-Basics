package com.thoughtworks.collection;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practice2 {

    public static void main(String[] args) {
        List<Integer> numbers = getNumbers();

        System.out.println("\nFor loop:");
        for (int i = 0, len = numbers.size(); i < len; i++) {
            System.out.print(numbers.get(i) + " ");
        }

        System.out.println("\nFor-each loop:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println("\nIterator:");
        Iterator<Integer> numbersIter = numbers.iterator();
        while (numbersIter.hasNext()) {
            System.out.print(numbersIter.next() + " ");
        }
    }

    public static List<Integer> getNumbers() {
        return IntStream.rangeClosed(0, 100).boxed().collect(Collectors.toList());
    }


}
