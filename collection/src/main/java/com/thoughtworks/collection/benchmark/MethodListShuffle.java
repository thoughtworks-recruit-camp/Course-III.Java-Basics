package com.thoughtworks.collection.benchmark;

import com.thoughtworks.collection.Practice1;

public class MethodListShuffle implements RandIntMethod {
    @Override
    public String getMethodName() {
        return "Method-List-Shuffle";
    }

    @Override
    public int methodBody(int min, int max, int count) {
        return (Practice1.getUniqueRandomIntegers(min, max, count).size());
    }
}
