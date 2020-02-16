package com.thoughtworks;

import com.thoughtworks.promotion.Promotion;
import com.thoughtworks.promotion.PromotionsProvider;

public class Restaurant {

    public String bestCheckOutForm(String selectedItems) {
        Order order = OrderParser.parseSelectedItems(selectedItems);
        CheckOut bestCheckOut = checkOutWithBestPromo(order);
        return CheckOutRenderer.renderCheckOutForm(bestCheckOut);
    }

    private static CheckOut checkOutWithBestPromo(Order order) {
        Promotion[] promotions = PromotionsProvider.getPromotions();
        Double finalCost = Double.MAX_VALUE;
        CheckOut bestCheckOut = null;

        for (Promotion promotion : promotions) {
            CheckOut checkOut = promotion.makeCheckOut(order);
            if (checkOut.getTotalCost() < finalCost) {
                finalCost = checkOut.getTotalCost();
                bestCheckOut = checkOut;
            }
        }
        return bestCheckOut;
    }
}
