package com.thoughtworks.numguess;

import java.util.List;

class Match {
    private final Guess guess;
    private final int fullMatch;
    private final int digitMatch;

    Match(Guess guess, Answer answer) {
        this.guess = guess;
        int fullMatch = 0;
        int digitMatch = 0;
        List<Integer> guessDigits = guess.getDigits();
        List<Integer> answerDigits = answer.getDigits();
        for (int i = 0, len = guessDigits.size(); i < len; i++) {
            int iAnswer = answerDigits.indexOf(guessDigits.get(i));  // guess digit's index in answer
            if (iAnswer == i) {
                fullMatch++;
            } else if (iAnswer > -1) {
                digitMatch++;
            }
        }
        this.fullMatch = fullMatch;
        this.digitMatch = digitMatch;
    }

    Guess getGuess() {
        return guess;
    }

    int getFullMatch() {
        return fullMatch;
    }

    int getDigitMatch() {
        return digitMatch;
    }
}
