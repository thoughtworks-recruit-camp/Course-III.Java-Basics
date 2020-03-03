package com.thoughtworks.numguess;

import com.thoughtworks.numguess.enums.Result;
import com.thoughtworks.numguess.exceptions.InvalidInput;
import com.thoughtworks.numguess.exceptions.InvalidLocalAnswer;
import com.thoughtworks.numguess.exceptions.InvalidStatus;

import com.thoughtworks.numguess.enums.Status;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleController {
    private final static Scanner in = new Scanner(System.in);
    private final static PrintStream out = new PrintStream(System.out);

    public static void startNewGame(Game game) {
        prepareGame(game);
        startGame(game);
        while (game.getStatus() != Status.Ended) {
            playGame(game);
        }
        showResult(game);
    }

    public static void showErrorAndExit(InvalidStatus invalidStatus) {
        out.println(invalidStatus.getMessage());
        System.exit(-1);
    }

    private static void showResult(Game game) {
        switch (game.getResult()) {
            case Won:
                out.println("Congratulations, you win!");
                break;
            case Lost:
                out.println(String.format("Unfortunately, you have no chance. The answer is %s.",
                        game.getAnswer().getLiteral()));
                break;
            case Quit:
                out.println(String.format("You quit the game.\nYou tried %d/%d times, The answer is %s. ",
                        game.getTriesMax() - game.getTriesLeft(), game.getTriesMax(), game.getAnswer().getLiteral()));
                break;
            case Unknown:
                out.println("Game result unknown");
                break;
        }
    }

    private static void startGame(Game game) {
        game.start();
        out.printf("Using %s answer, please make a guess of %d digits.\nType QUIT any time to leave the game.\n",
                game.getAnswerType().toString().toLowerCase(), game.getAnswerLen());
    }

    private static void prepareGame(Game game) {
        try {
            game.load();
        } catch (InvalidLocalAnswer e) {
            out.println(e.getMessage());
        }
    }

    private static void playGame(Game game) {
        String input = in.next();
        String lastInvalidInput = "";
        if (input.equals("QUIT")) {
            game.quit();
        } else {
            try {
                game.handleInput(input);

            } catch (InvalidInput e) {
                lastInvalidInput = String.format("%s %s\n", input, e.getMessage());
            } finally {
                printResultsHistory(game);
                out.print(lastInvalidInput);
            }
        }
    }

    private static void printResultsHistory(Game game) {
        game.getResultsHistory().forEach(match -> out.println(String.format("%s %dA%dB",
                match.getGuess().getLiteral(), match.getFullMatch(), match.getDigitMatch())));
    }

}
