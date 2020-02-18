package com.thoughtworks.collection.benchmark;

import com.thoughtworks.collection.Practice1;

public class MethodHashSetRandom implements RandIntMethod {
    @Override
    public String getMethodName() {
        return "Method-HashSet-random";
    }

    @Override
    public int methodBody(int min, int max, int count) {
        return (Practice1.getUniqueRandomIntegersAlt(min, max, count).size());
    }
}
