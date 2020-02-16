package com.thoughtworks;

import com.thoughtworks.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Entry> orderEntries;
    private double totalCost;

    public Order() {
        orderEntries = new ArrayList<>();
        totalCost = 0;
    }

    public List<Entry> getOrderEntries() {
        return orderEntries;
    }

    public void addEntry(Entry entry) {
        orderEntries.add(entry);
        totalCost += entry.getSubTotal();
    }

    public double getTotalCost() {
        return totalCost;
    }

}
