package ru.otus.glavs.service;

import ru.otus.glavs.domain.Quiz;

import java.util.List;

public interface QuizService {
    void displayAllQuestions();

    void displayQuestion(Quiz question);

    List<Quiz> getQuestionList();

    Quiz getQuestionById(int id);

    String getAnswerByNumber(Quiz quiz, int number);
}
