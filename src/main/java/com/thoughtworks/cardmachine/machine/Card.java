package com.thoughtworks.cardmachine.machine;

public class Card implements CardTerm {
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