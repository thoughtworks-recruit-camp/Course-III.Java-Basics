package com.thoughtworks.account.controller;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;


public abstract class ScannerFilter implements Iterator<String>, Closeable {
    protected final Iterator<String> sc;

    public ScannerFilter(Iterator<String> sc) {
        this.sc = sc;
    }

    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public String next() {
        String next = sc.next();
        filter(next);
        return next;
    }

    public abstract void filter(String input);
}
