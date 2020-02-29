package com.thoughtworks;

import com.thoughtworks.numguess.ConsoleController;
import com.thoughtworks.numguess.Game;
import com.thoughtworks.numguess.configs.ConfigLoader;
import com.thoughtworks.numguess.exceptions.InvalidStatus;

public final class RandomAnswerDemo {
    public static void main(String[] args) {
        try {
            ConsoleController.startNewGame(new Game(ConfigLoader.loadBrokenConfig()));
        } catch (InvalidStatus e) {
            ConsoleController.showErrorAndExit(e);
        }
    }
}
