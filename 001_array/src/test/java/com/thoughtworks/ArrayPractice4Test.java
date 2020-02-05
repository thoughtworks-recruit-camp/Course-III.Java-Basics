package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPractice4Test {

    @Test
    void should_insert_correctly_with_10() {
        int[] result = ArrayPractice4.insert(10);

        int[] except = new int[]{1, 10, 20, 50, 100};

        assertArrayEquals(except, result);
    }

    @Test
    void should_insert_correctly_with_0() {
        int[] result = ArrayPractice4.insert(0);

        int[] except = new int[]{0, 1, 20, 50, 100};

        assertArrayEquals(except, result);
    }

    @Test
    void should_insert_correctly_with_200() {
        int[] result = ArrayPractice4.insert(200);

        int[] except = new int[]{1, 20, 50, 100, 200};

        assertArrayEquals(except, result);
    }
}