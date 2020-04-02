package com.thoughtworks.account.controller;

import com.thoughtworks.account.errors.ExitEvent;

import java.util.Iterator;

class BasicScannerFilter extends ScannerFilter {
    public BasicScannerFilter(Iterator<String> sc) {
        super(sc);
    }

    @Override
    public void filter(String input) {
        if (input.equals("EXIT")) {
            throw new ExitEvent();
        }
    }
}
