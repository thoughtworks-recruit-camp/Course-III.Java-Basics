package com.truman.games.bullsncows.models;

public class ResultDetails {
    private final Result result;
    private final Digits answer;
    private final int triesUsed;
    private final int triesMax;

    public ResultDetails(Result result, Digits answer, int triesMax, int triesLeft) {
        this.result = result;
        this.answer = answer;
        this.triesUsed = triesMax - triesLeft;
        this.triesMax = triesMax;
    }

    public int getTriesUsed() {
        return triesUsed;
    }

    public int getTriesMax() {
        return triesMax;
    }

    public Result getResult() {
        return result;
    }

    public Digits getAnswer() {
        return answer;
    }
}
