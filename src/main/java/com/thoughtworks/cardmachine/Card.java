package com.thoughtworks.cardmachine;

import com.thoughtworks.cardmachine.CommonName;
import com.thoughtworks.cardmachine.Rank;
import com.thoughtworks.cardmachine.Suit;

public class Card implements CommonName {
    private final Suit suit;
    private final Rank rank;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return this.suit.toString() + this.rank.toString();
    }
}