package com.thoughtworks.cardmachine.machine;

import java.util.ArrayList;
import java.util.Collections;

public class CardMachine {
    private final ArrayList<Card> initDeck;  // cache initial deck
    private ArrayList<Card> deck;

    public CardMachine() {
        this.initDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                this.initDeck.add(new Card(suit, rank));
            }
        }
    }

    public int getDeckSize() {
        return deck.size();
    }

    public int reloadDeck() {
        this.deck = new ArrayList<>(initDeck);
        Collections.shuffle(this.deck);
        return 0;
    }

    public ArrayList<Card> drawCards(int drawCount) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < drawCount; i++) {
            cards.add(this.deck.remove(this.deck.size() - 1));  // Draw last card
        }
        return cards;
    }
}