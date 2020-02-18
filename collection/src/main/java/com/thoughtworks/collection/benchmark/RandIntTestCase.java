package com.thoughtworks.collection.benchmark;

public class RandIntTestCase implements TestCase {
    final int min;
    final int max;
    final int count;

    RandIntTestCase(int min, int max, int count) {
        this.min = min;
        this.max = max;
        this.count = count;
    }

    @Override
    public String getTestInfo() {
        return String.format("\n- Get %d random integers between [%d, %d]: ", count, min, max);
    }
}
