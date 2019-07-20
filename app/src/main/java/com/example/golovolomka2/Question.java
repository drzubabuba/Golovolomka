package com.example.golovolomka2;

public class Question {

    private String firstHint;
    private String secondHint;
    private String answer;

    public String getFirstHint() {
        return firstHint;
    }

    public void setFirstHint(String firstHint) {
        this.firstHint = firstHint;
    }

    public String getSecondHint() {
        return secondHint;
    }

    public void setSecondHint(String secondHint) {
        this.secondHint = secondHint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question(String firstHint, String secondHint, String answer) {
        this.firstHint = firstHint;
        this.secondHint = secondHint;
        this.answer = answer;
    }
}
