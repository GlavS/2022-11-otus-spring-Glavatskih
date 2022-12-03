package ru.otus.glavs.service;

import ru.otus.glavs.domain.Quiz;

import java.util.List;

public interface QuizService {
    void displayAll();
    List<Quiz> getQuestionList();
}
