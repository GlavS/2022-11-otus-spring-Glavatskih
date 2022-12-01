package ru.otus.glavs.domain;

public class Quiz {
    private final int id;
    private final String question;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final int correctAnswer;


    public Quiz(int id, String question, String answer1, String answer2, String answer3, int correctAnswer) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
