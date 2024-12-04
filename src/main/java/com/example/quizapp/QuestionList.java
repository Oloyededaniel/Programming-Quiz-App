package com.example.quizapp;

public class QuestionList {
    private String question,Option1,Option2,Option3,Option4,answer;
    private String userSelectedAnswer;

    public QuestionList(String question, String option1, String option2, String option3, String option4, String answer, String userSelectedAnswer) {
        this.question = question;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        this.answer = answer;
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }
}
