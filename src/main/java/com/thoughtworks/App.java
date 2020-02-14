package com.thoughtworks;

import com.thoughtworks.cardmachine.CardMachine;

public class App {

    public static void main(String[] args) {
        CardMachine cardMachine = new CardMachine();
        while (true) {
            cardMachine.drawCardsAndShow();
        }
    }
}
