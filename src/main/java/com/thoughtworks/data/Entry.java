package com.thoughtworks.data;

public class Entry {
    private final Dish dish;
    private final int quantity;

    private final double subTotal;

    public Entry(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.subTotal = dish.getPrice() * quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }
}
