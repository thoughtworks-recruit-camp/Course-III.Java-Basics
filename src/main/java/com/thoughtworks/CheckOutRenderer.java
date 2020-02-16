package com.thoughtworks;

import com.thoughtworks.data.Entry;

public class CheckOutRenderer {
    public static String renderCheckOutForm(CheckOut checkOut) {
        StringBuilder checkOutFormStr = new StringBuilder("============= 订餐明细 =============\n");
        for (Entry entry : checkOut.getEntries()) {
            checkOutFormStr.append(String.format("%s x %d = %.0f元\n",
                    entry.getDish().getName(),
                    entry.getQuantity(),
                    entry.getDish().getPrice() * entry.getQuantity()));
        }
        checkOutFormStr.append("-----------------------------------\n");

        checkOutFormStr.append(checkOut.getPromoInfo().equals("") ? "" :
                String.format("使用优惠:\n%s\n-----------------------------------\n", checkOut.getPromoInfo()));

        checkOutFormStr.append(String.format("总计：%.0f元\n===================================", checkOut.getTotalCost()));

        return checkOutFormStr.toString();
    }
}
