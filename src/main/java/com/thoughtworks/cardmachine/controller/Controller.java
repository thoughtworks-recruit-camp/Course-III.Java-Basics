package com.thoughtworks.cardmachine.controller;

import com.thoughtworks.cardmachine.machine.Card;
import com.thoughtworks.cardmachine.machine.CardMachine;

import java.util.ArrayList;

abstract class Controller {
    CardMachine cardMachine;

    Controller(CardMachine cardMachine) {
        this.cardMachine = cardMachine;
        cardMachine.reloadDeck();
    }

    protected abstract int getValidCount();

    ArrayList<Card> getCards(int count) {
        return cardMachine.drawCards(count);
    }
}
