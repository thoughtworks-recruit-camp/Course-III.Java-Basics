package com.truman.games.bullsncows.controllers;


import com.truman.games.bullsncows.*;
import com.truman.games.bullsncows.exceptions.GamesCountExceeded;
import com.truman.games.bullsncows.exceptions.InvalidInput;
import com.truman.games.bullsncows.exceptions.InvalidLocalAnswer;
import com.truman.games.bullsncows.exceptions.InvalidStatus;
import com.truman.games.bullsncows.models.AnswerInfo;
import com.truman.games.bullsncows.models.ResultDetails;
import com.truman.games.bullsncows.models.Status;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleController {
    private final static Scanner in = new Scanner(System.in);
    private final static PrintStream out = new PrintStream(System.out);
    private static GameManager gameManager = GameManager.instance();

    public static void playNewGame() throws InvalidStatus, GamesCountExceeded {
        playNewGame(null);
    }

    public static void playNewGame(File answerFile) throws InvalidStatus, GamesCountExceeded {
        Game game = gameManager.getNewGame(answerFile);
        prepareGame(game);
        startGame(game);
        while (game.getStatusDetails().getStatus() != Status.Ended) {
            playGame(game);
        }
        showResult(game);
    }

    public static void showErrorAndExit(Exception invalidStatus) {
        out.println(invalidStatus.getMessage());
        System.exit(-1);
    }

    private static void prepareGame(Game game) {
        try {
            game.loadAnswer();
        } catch (InvalidLocalAnswer | InvalidStatus e) {
            out.println(e.getMessage());
        }
    }

    private static void startGame(Game game) throws InvalidStatus {
        game.start();
        AnswerInfo answerInfo = game.getAnswerInfo();
        out.printf("Using %s answer, please make a guess of %d digits.\nType QUIT any time to leave the game.\n",
                answerInfo.getAnswerType(), answerInfo.getAnswerLen());
    }

    private static void playGame(Game game) {
        String input = in.next();
        String lastInvalidInput = "";
        if (input.equals("QUIT")) {
            game.quit();
        } else {
            try {
                game.handleInput(input);
            } catch (InvalidInput | InvalidStatus e) {
                lastInvalidInput = String.format("%s %s\n", input, e.getMessage());
            } finally {
                printResultsHistory(game);
                out.print(lastInvalidInput);
            }
        }
    }

    private static void showResult(Game game) throws InvalidStatus {
        ResultDetails resultDetails = game.getResultDetails();
        switch (resultDetails.getResult()) {
            case Won:
                out.println("Congratulations, you win!");
                break;
            case Lost:
                out.println(String.format("Unfortunately, you have no chance. The answer is %s.",
                        resultDetails.getAnswer()));
                break;
            case Quit:
                out.println(String.format("You quit the game.\nYou tried %d/%d times, The answer is %s. ",
                        resultDetails.getTriesUsed(), resultDetails.getTriesMax(), resultDetails.getAnswer()));
                break;
            case Unknown:
                out.println("Game result unknown");
                break;
        }
    }

    private static void printResultsHistory(Game game) {
        game.getMatchHistory().forEach(match -> out.println(String.format("%s %dA%dB",
                match.getGuess().getLiteral(), match.getFullMatch(), match.getDigitMatch())));
    }
}
