package com.thoughtworks.account.controller;

import java.util.Iterator;
import java.util.function.Consumer;

// for mock purpose
public class EmptyStringIterator implements Iterator<String> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public void remove() {
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
    }
}
