package com.thoughtworks.cardmachine;

public enum Suit implements CommonName {
    Hearts("红桃"),
    Diamonds("方片"),
    Clubs("梅花"),
    Spades("黑桃");
    private final String suitStr;

    Suit(String suitStr) {
        this.suitStr = suitStr;
    }

    @Override
    public String toString() {
        return this.suitStr;
    }
}
