package com.thoughtworks.cardmachine.controller;

import com.thoughtworks.cardmachine.machine.Card;
import com.thoughtworks.cardmachine.machine.CardMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleController extends Controller {
    private static Scanner sc;

    public ConsoleController(CardMachine cardMachine) {
        super(cardMachine);
        sc = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.printf("请输入抽牌张数（牌堆共有%d张牌）<输入Exit退出，输入Reload重取一副新牌并洗牌>\n", cardMachine.getDeckSize());
            int count = getValidCount();
            switch (count) {
                case -1:
                    System.exit(0);
                case 0:
                    cardMachine.reloadDeck();
                    System.out.println("已取一副新牌并洗牌");
                    break;
                default:
                    System.out.printf("抽取的牌为:\n%s\n剩余%d张牌\n\n", printCards(getCards(count)), cardMachine.getDeckSize());
            }
        }
    }

    protected int getValidCount() {
        int curDeckSize = cardMachine.getDeckSize();
        while (true) {
            if (sc.hasNextInt()) {  // validate count
                int input = sc.nextInt();
                if (input <= 0 || input > curDeckSize) {
                    System.out.printf("输入张数错误，请重新输入张数（牌堆共有%d张牌）<输入Exit退出，输入Reload重取一副新牌并洗牌>\n", curDeckSize);
                } else {
                    return input;
                }
            } else {  // execute command
                switch (sc.next()) {
                    case "Exit":
                        return -1;
                    case "Reload":
                        return 0;
                    default:
                        System.out.println("输入无效，请重新输入：");
                }
            }
        }
    }

    private static String printCards(ArrayList<Card> cards) {
        ArrayList<String> cardStrings = new ArrayList<>();
        for (Card card : cards) {
            cardStrings.add(card.toString());
        }
        return String.join(" ", cardStrings);
    }
}
