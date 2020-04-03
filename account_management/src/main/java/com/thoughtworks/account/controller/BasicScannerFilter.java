package com.thoughtworks.account.controller;

import com.thoughtworks.account.exceptions.ExitEvent;

import java.util.Iterator;

class BasicScannerFilter extends ScannerFilter {
    public BasicScannerFilter(Iterator<String> scannerLike) {
        super(scannerLike);
    }

    @Override
    public void filter(String input) {
        if (input.equals("EXIT")) {
            throw new ExitEvent();
        }
    }
}
