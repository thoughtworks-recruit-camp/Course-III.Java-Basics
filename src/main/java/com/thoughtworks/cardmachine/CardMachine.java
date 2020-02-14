package com.thoughtworks.cardmachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
        this.reloadDeck();
    }

    private void reloadDeck() {
        reloadDeck(true);
    }

    private void reloadDeck(boolean withShuffle) {
        this.deck = new ArrayList<>(initDeck);
        System.out.println("已取一副新牌");
        if (withShuffle) {
            Collections.shuffle(this.deck);
            System.out.println("已洗牌");
        }
    }

    public void drawCardsAndShow() {
        System.out.printf("请输入抽牌张数（牌堆共有%d张牌），或输入Exit退出：\n", this.deck.size());
        int count = 0;
        while (count == 0) {
            count = getValidCountOrExit();
        }
        System.out.printf("抽取的牌为:\n%s\n剩余%d张牌\n\n", showCards(drawCards(count)), this.deck.size());
    }

    private Card[] drawCards(int drawCount) {
        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < drawCount; i++) {
            cards.add(this.deck.remove(this.deck.size() - 1));  // Draw last card
        }
        return cards.toArray(new Card[0]);
    }

    private int getValidCountOrExit() {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            count = sc.nextInt();
            while (count <= 0 || count > this.deck.size()) {
                System.out.printf("输入张数错误，请重新输入（牌堆共有%d张牌），或输入Reload重取一副新牌并洗牌：\n", this.deck.size());
                if (sc.hasNextInt()) {
                    count = sc.nextInt();
                } else {
                    if (sc.next().equals("Reload")) {
                        this.reloadDeck();
                    }
                }
            }
        } else {
            if (sc.next().equals("Exit")) {
                System.exit(0);
            }
            System.out.println("输入非整数，请重新输入：");
        }
        return count;
    }

    private static String showCards(Card[] cards) {
        ArrayList<String> cardStrings = new ArrayList<>();
        for (Card card : cards) {
            cardStrings.add(card.toString());
        }
        return String.join(" ", cardStrings);
    }
}