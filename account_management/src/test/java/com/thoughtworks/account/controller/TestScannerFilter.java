package com.thoughtworks.account.controller;

import java.io.PrintStream;
import java.util.Iterator;

// 将模拟用户输入重定向至输出，方便查看交互记录
public final class TestScannerFilter extends BasicScannerFilter {
    protected final PrintStream reOut;

    public TestScannerFilter(Iterator<String> sc, PrintStream reOut) {
        super(sc);
        this.reOut = reOut;
    }

    public String next() {
        String input = sc.next();
        reOut.printf("[输入]: %s\n", input);
        filter(input);
        return input;
    }
}
