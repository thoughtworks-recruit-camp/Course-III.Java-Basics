package com.truman.games.bullsncows.models;

public class Match {
    private final Digits guess;
    private final int fullMatch;
    private final int digitMatch;

    public Match(Digits guess, int fullMatch, int digitMatch) {
        this.guess = guess;
        this.fullMatch = fullMatch;
        this.digitMatch = digitMatch;
    }

    public Digits getGuess() {
        return guess;
    }

    public int getFullMatch() {
        return fullMatch;
    }

    public int getDigitMatch() {
        return digitMatch;
    }
}
