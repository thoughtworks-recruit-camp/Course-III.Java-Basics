package com.truman.games.bullsncows.models;

public class AnswerInfo {
    private final AnswerType answerType;
    private final int answerLen;

    public AnswerInfo(AnswerType answerType, int answerLen) {
        this.answerType = answerType;
        this.answerLen = answerLen;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public int getAnswerLen() {
        return answerLen;
    }
}
