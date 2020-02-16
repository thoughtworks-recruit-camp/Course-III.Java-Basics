package com.thoughtworks.promotion;

public class PromotionsProvider {
    public static Promotion[] getPromotions() {
        return new Promotion[]{new NoPromotion(), new SimplePromotion(), new HalfPromotion()};
    }
}
