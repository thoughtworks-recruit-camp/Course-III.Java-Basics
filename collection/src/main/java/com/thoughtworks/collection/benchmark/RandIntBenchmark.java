package com.thoughtworks.collection.benchmark;

public class RandIntBenchmark {
    public static void main(String[] args) {
        RandIntMethod[] methods = new RandIntMethod[]{new MethodListShuffle(), new MethodHashSetRandom()};
        RandIntTestCase[] testCases = new RandIntTestCase[]{new RandIntTestCase(0, 10000, 1000),
                new RandIntTestCase(0, 10000, 5000),
                new RandIntTestCase(0, 10000, 9000),
                new RandIntTestCase(0, 10000000, 1000000),
                new RandIntTestCase(0, 10000000, 5000000),
                new RandIntTestCase(0, 10000000, 9000000)};

        for (RandIntTestCase testCase : testCases) {
            System.out.println(testCase.getTestInfo());
            for (RandIntMethod method : methods) {
                runBenchmark(method, testCase.min, testCase.max, testCase.count);
            }
        }
    }

    private static void runBenchmark(RandIntMethod method, int min, int max, int count) {
        long timeBegin, timeEnd, timePassed;
        timeBegin = System.currentTimeMillis();
        int size = method.methodBody(min, max, count);
        timeEnd = System.currentTimeMillis();
        timePassed = timeEnd - timeBegin;
        System.out.printf("  - [%.0f%% Fill Ratio] %s: %dms\n", (double) size / max * 100, method.getMethodName(), timePassed);
    }
}
