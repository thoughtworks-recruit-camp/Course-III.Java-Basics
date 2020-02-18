package com.thoughtworks.collection.benchmark;

public interface RandIntMethod {
    String getMethodName();

    int methodBody(int min, int max, int count);
}
