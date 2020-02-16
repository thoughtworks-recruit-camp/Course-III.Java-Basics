package com.thoughtworks;

import com.thoughtworks.cardmachine.machine.CardMachine;
import com.thoughtworks.cardmachine.controller.ConsoleController;

public class App {
    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController(new CardMachine());
        controller.run();
    }
}
