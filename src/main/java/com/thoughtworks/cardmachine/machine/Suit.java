package com.thoughtworks.cardmachine.machine;

public enum Suit implements CardTerm {
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
