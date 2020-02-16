package com.thoughtworks.promotion;

import com.thoughtworks.*;
import com.thoughtworks.data.DataProvider;
import com.thoughtworks.data.Dish;
import com.thoughtworks.data.Entry;

import java.util.ArrayList;
import java.util.HashSet;

public class HalfPromotion implements Promotion {
    @Override
    public CheckOut makeCheckOut(Order order) {
        HashSet<String> promoSet = DataProvider.getHalfDishIdsSet();
        ArrayList<String> promoDishNames = new ArrayList<>();
        double discount = 0;

        for (Entry entry : order.getOrderEntries()) {
            Dish dish = entry.getDish();
            int quantity = entry.getQuantity();
            if (promoSet.contains(dish.getId())) {
                promoDishNames.add(dish.getName());
                discount += dish.getPrice() * quantity / 2;
            }
        }
        String promoInfo = String.format("指定菜品半价(%s)，省%.0f元", String.join("，", promoDishNames), discount);
        return new CheckOut(order, promoInfo, order.getTotalCost() - discount);
    }
}
