package com.truman.games;

import com.truman.games.bullsncows.controllers.ConsoleController;
import com.truman.games.bullsncows.exceptions.GamesCountExceeded;
import com.truman.games.bullsncows.exceptions.InvalidStatus;

import java.io.File;

public final class BullsNCowsDemo {
    public static void main(String[] args) {
        System.out.println("\n*** Demo for local answer 1234 ***\n");
        try {
            ConsoleController.playNewGame(new File("src/main/resources/answer.txt"));
        } catch (InvalidStatus | GamesCountExceeded e) {
            ConsoleController.showErrorAndExit(e);
        }
        
        System.out.println("\n*** Demo for random answer (due to wrong local answer path) ***\n");
        try {
            ConsoleController.playNewGame();
        } catch (InvalidStatus | GamesCountExceeded e) {
            ConsoleController.showErrorAndExit(e);
        }
    }
}
