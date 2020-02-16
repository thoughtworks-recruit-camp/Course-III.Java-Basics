package com.thoughtworks;

import com.thoughtworks.data.Entry;

import java.util.Collections;
import java.util.List;

public class CheckOut {
    private final List<Entry> entries;
    private final String promoInfo;
    private final Double totalCost;

    public CheckOut(Order order, String promoInfo, Double totalCost) {
        this.entries = Collections.unmodifiableList(order.getOrderEntries());
        this.promoInfo = promoInfo;
        this.totalCost = totalCost;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public String getPromoInfo() {
        return promoInfo;
    }

}
