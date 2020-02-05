package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPractice2Test {
    @Test
    void should_return_int_array_correctly() {
        int[] result = ArrayPractice2.exchange();
        int[] excepted = new int[]{20, 8, 1, 7, 0, 10, 16, 19};

        assertArrayEquals(excepted, result);
    }
}