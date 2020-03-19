package com.thoughtworks.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Flatten {

    Integer[][] array;
    public Flatten(Integer[][] array) {
        this.array = array;
    }

    public List<Integer> transformToOneDimensional() {
        return Arrays.stream(array)
                .flatMapToInt(intArr -> Arrays.stream(intArr)
                        .mapToInt(Integer::intValue))
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> transformToUnrepeatedOneDimensional() {
        return Arrays.stream(array)
                .flatMapToInt(integers -> Arrays.stream(integers)
                        .mapToInt(Integer::intValue))
                .boxed()
                .distinct()
                .collect(Collectors.toList());
    }
}
