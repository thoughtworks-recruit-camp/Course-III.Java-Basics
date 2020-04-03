package com.thoughtworks.account.controller;

import java.util.Iterator;

public abstract class ScannerFilter implements Iterator<String>{
    protected final Iterator<String> sc;

    public ScannerFilter(Iterator<String> scannerLike) {
        this.sc = scannerLike;
    }

    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }

    @Override
    public String next() {
        String next = sc.next();
        filter(next);
        return next;
    }

    public abstract void filter(String input);
}
