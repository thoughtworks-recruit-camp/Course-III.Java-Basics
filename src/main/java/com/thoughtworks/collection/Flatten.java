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
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    public List<Integer> transformToUnrepeatedOneDimensional() {
        return Arrays.stream(array)
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
