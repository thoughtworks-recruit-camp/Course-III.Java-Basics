package com.thoughtworks.numguess;

import com.thoughtworks.numguess.configs.Config;
import com.thoughtworks.numguess.configs.ConfigLoader;
import com.thoughtworks.numguess.exceptions.*;
import com.thoughtworks.numguess.enums.*;

import java.io.File;
import java.util.ArrayList;

public class Game {
    private final int answerLen;
    private final File answerFile;
    private final int triesMax;
    private int triesLeft;
    private AnswerType answerType;
    private Answer answer;
    private Status status = Status.Preparing;
    private Result result = Result.Unknown;
    private ArrayList<Match> resultsHistory = new ArrayList<>();

    public Game(Config config) {
        this.answerLen = config.getAnswerLen();
        this.answerFile = config.getAnswerFile();
        triesLeft = this.triesMax = config.getTriesMax();
    }

    public Game() {
        this(ConfigLoader.loadDefaultConfig());
    }

    int getTriesMax() {
        return triesMax;
    }

    int getTriesLeft() {
        return triesLeft;
    }

    Answer getAnswer() {
        return answer;
    }

    int getAnswerLen() {
        return answerLen;
    }

    ArrayList<Match> getResultsHistory() {
        return resultsHistory;
    }

    void load() throws InvalidLocalAnswer {
        try {
            answer = new LocalAnswer(answerFile, answerLen);
            answerType = AnswerType.Local;
        } catch (InvalidLocalAnswer e) {
            answer = new RandomAnswer(answerLen);
            answerType = AnswerType.Random;
            throw e;
        } finally {
            changeStatus(Status.Preparing, Status.Ready, "Load Answer");
        }
    }

    AnswerType getAnswerType() {
        return answerType;
    }

    void start() {
        changeStatus(Status.Ready, Status.Ongoing, "Start Game");
    }

    private void changeStatus(Status before, Status after, String desc) {
        if (status == before) {
            status = after;
        } else {
            throw new InvalidStatus(before, status, desc);
        }
    }

    private void checkStatus(Status present, String desc) {
        if (status != present) {
            throw new InvalidStatus(present, status, desc);
        }
    }

    void handleInput(String input) throws InvalidInput {
        checkStatus(Status.Ongoing, "Handle Input");
        if (input.equals("QUIT")) {
            status = Status.Ended;
            result = Result.Quit;
            return;
        }
        try {
            Guess guess = new Guess(input, answerLen);
            Match match = new Match(guess, answer);
            resultsHistory.add(match);
            checkMatch(match);
        } catch (InvalidFormat e) {
            throw new InvalidInput(e);
        }
    }

    private void checkMatch(Match match) {
        if (match.getFullMatch() == 4) {
            status = Status.Ended;
            result = Result.Won;
        }
        if (--triesLeft == 0) {
            status = Status.Ended;
            result = Result.Lost;
        }
    }

    Status getStatus() {
        return status;
    }

    Result getResult() {
        checkStatus(Status.Ended, "Get Result");
        return result;
    }
}
