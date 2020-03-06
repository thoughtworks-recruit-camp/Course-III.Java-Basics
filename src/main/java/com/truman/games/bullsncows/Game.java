package com.truman.games.bullsncows;

import com.truman.games.bullsncows.exceptions.*;
import com.truman.games.bullsncows.models.*;
import com.truman.games.bullsncows.models.AnswerType;
import com.truman.games.bullsncows.models.Result;
import com.truman.games.bullsncows.models.Status;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private static final int ANSWER_LEN = 4;
    private static final int TRIES_MAX = 6;
    private final Optional<File> answerFile;
    private AnswerType answerType;
    private Digits answer;
    private int triesLeft = TRIES_MAX;
    private Status status = Status.Preparing;
    private Result result = Result.Unknown;
    private boolean resultRetrieved = false;
    private ArrayList<Match> resultsHistory = new ArrayList<>();
    private long lastActiveTime;

    Game() {
        this(null);
    }

    Game(File answerFile) {
        this.answerFile = Optional.ofNullable(answerFile);
        updateActiveTime();
    }

    public AnswerInfo getAnswerInfo() {
        return new AnswerInfo(answerType, ANSWER_LEN);
    }

    public StatusDetails getStatusDetails() {
        return new StatusDetails(TRIES_MAX, triesLeft, status, lastActiveTime);
    }

    public ArrayList<Match> getMatchHistory() {
        return resultsHistory;
    }

    public ResultDetails getResultDetails() throws InvalidStatus {
        checkStatus(Status.Ended, "Get Result");
        resultRetrieved = true;
        return new ResultDetails(result, answer, TRIES_MAX, triesLeft);
    }

    boolean isResultRetrieved() {
        return resultRetrieved;
    }

    public void loadAnswer() throws InvalidLocalAnswer, InvalidStatus {
        if (answerFile.isPresent()) {
            try {
                loadLocalAnswer();
            } catch (InvalidLocalAnswer e) {
                loadRandomAnswer();
                throw e;
            } finally {
                changeStatus(Status.Preparing, Status.Ready, "Load Answer");
            }
        } else {
            loadRandomAnswer();
            changeStatus(Status.Preparing, Status.Ready, "Load Answer");
        }

    }

    public void start() throws InvalidStatus {
        changeStatus(Status.Ready, Status.Ongoing, "Start Game");
    }

    public void handleInput(String input) throws InvalidInput, InvalidStatus {
        updateActiveTime();
        checkStatus(Status.Ongoing, "Handle Input");
        try {
            Digits guess = validateToDigits(input);
            Match match = checkMatch(guess);
            resultsHistory.add(match);
            if (match.getFullMatch() == 4) {
                status = Status.Ended;
                result = Result.Won;
            }
            if (--triesLeft == 0) {
                status = Status.Ended;
                result = Result.Lost;
            }
        } catch (InvalidFormat e) {
            throw new InvalidInput(e);
        }
    }

    public void quit() {
        status = Status.Ended;
        result = Result.Quit;
    }


    private Digits validateToDigits(String input) throws InvalidFormat {
        if (input.length() != ANSWER_LEN) {  // Validate Length
            throw new InvalidFormat(input.length());
        }
        char[] inputChars = input.toCharArray();
        Set<Integer> uniqueDigits = new LinkedHashSet<>();
        for (char inputChar : inputChars) {
            int digit = Character.getNumericValue(inputChar);
            if (digit < 0 || digit > 9) {  // Validate Digit
                throw new InvalidFormat(inputChar);
            }
            if (uniqueDigits.contains(digit)) {  // Validate Duplicates
                throw new InvalidFormat(String.format("Duplicate digit: [%d]", digit));
            }
            uniqueDigits.add(digit);
        }
        return new Digits(new LinkedList<>(uniqueDigits));
    }

    private void loadLocalAnswer() throws InvalidLocalAnswer {
        try (BufferedReader reader = new BufferedReader(new FileReader(answerFile.get()))) {
            answer = validateToDigits(reader.readLine());
            answerType = AnswerType.Local;
        } catch (InvalidFormat e) {
            throw new InvalidLocalAnswer(e);
        } catch (FileNotFoundException e) {
            throw new InvalidLocalAnswer(new FileNotFoundException(String.format("Answer file not found: %s", answerFile)));
        } catch (IOException e) {
            throw new InvalidLocalAnswer(new IOException(String.format("Failed loading answer file: %s", answerFile)));
        }
    }

    private void loadRandomAnswer() {
        List<Integer> digits = new Random().ints(0, 10).boxed().distinct().limit(ANSWER_LEN).collect(Collectors.toCollection(LinkedList::new));
        this.answer = new Digits(digits);
        answerType = AnswerType.Random;
    }

    private Match checkMatch(Digits guess) {
        List<Integer> guessDigits = guess.getDigits();
        List<Integer> answerDigits = answer.getDigits();
        int fullMatch = 0;
        int digitMatch = 0;
        for (int i = 0, len = guessDigits.size(); i < len; i++) {
            int iAnswer = answerDigits.indexOf(guessDigits.get(i));  // guess digit's index in answer
            if (iAnswer == i) {
                fullMatch++;
            } else if (iAnswer > -1) {
                digitMatch++;
            }
        }
        return new Match(guess, fullMatch, digitMatch);
    }

    private void changeStatus(Status required, Status target, String desc) throws InvalidStatus {
        checkStatus(required, desc);
        status = target;
    }

    private void checkStatus(Status required, String desc) throws InvalidStatus {
        if (status != required) {
            throw new InvalidStatus(required, status, desc);
        }
    }

    private void updateActiveTime() {
        lastActiveTime = new Date().getTime();
    }
}
