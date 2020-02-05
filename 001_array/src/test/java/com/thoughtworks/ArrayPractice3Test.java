package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPractice3Test {

    @Test
    void should_filter_zero_successful() {
        int[] array = new int[]{1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] result = ArrayPractice3.filterZero(array);

        int[] except = new int[]{1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};

        assertArrayEquals(except, result);
    }

    @Test
    void should_filter_zero_successful_with_array_not_includes_zero() {
        int[] array = new int[]{1, 3, 4, 5};
        int[] result = ArrayPractice3.filterZero(array);

        int[] except = new int[]{1, 3, 4, 5};

        assertArrayEquals(except, result);
    }

    @Test
    void should_filter_zero_successful_with_empty_array() {
        int[] array = new int[]{};
        int[] result = ArrayPractice3.filterZero(array);

        int[] except = new int[]{};

        assertArrayEquals(except, result);
    }
}