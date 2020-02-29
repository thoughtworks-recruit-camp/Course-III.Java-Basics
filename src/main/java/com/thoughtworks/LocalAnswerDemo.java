package com.thoughtworks;

import com.thoughtworks.numguess.ConsoleController;
import com.thoughtworks.numguess.Game;
import com.thoughtworks.numguess.exceptions.InvalidStatus;

public final class LocalAnswerDemo {
    public static void main(String[] args) {
        try {
            ConsoleController.startNewGame(new Game());
        } catch (InvalidStatus e) {
            ConsoleController.showErrorAndExit(e);
        }
    }
}
