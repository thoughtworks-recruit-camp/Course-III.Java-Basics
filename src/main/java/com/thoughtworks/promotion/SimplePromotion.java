package com.thoughtworks.promotion;

import com.thoughtworks.CheckOut;
import com.thoughtworks.Order;

public class SimplePromotion implements Promotion {
    @Override
    public CheckOut makeCheckOut(Order order) {
        double totalCost = order.getTotalCost();
        return new CheckOut(order, "满30减6元，省6元",totalCost >= 30 ? totalCost - 6 : totalCost);
    }
}
