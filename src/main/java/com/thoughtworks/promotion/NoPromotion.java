package com.thoughtworks.promotion;

import com.thoughtworks.CheckOut;
import com.thoughtworks.Order;

public class NoPromotion implements Promotion {
    @Override
    public CheckOut makeCheckOut(Order order) {
        return new CheckOut(order, "", order.getTotalCost());
    }
}
