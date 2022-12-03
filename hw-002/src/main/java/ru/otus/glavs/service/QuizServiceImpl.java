package ru.otus.glavs.service;

import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.domain.Quiz;

import java.util.List;

public class QuizServiceImpl implements QuizService{
    private final QuizDao dao;

    public QuizServiceImpl(QuizDao dao) {
        this.dao = dao;
    }
    @Override
    public void displayAll() {
        List<Quiz> quizList = dao.getAll();
        for (Quiz quiz :
                quizList) {
            System.out.printf("Question %d: %s%n", quiz.getId(), quiz.getQuestion());
            System.out.printf("   * Variant 1: %s%n", quiz.getAnswer1());
            System.out.printf("   * Variant 2: %s%n", quiz.getAnswer2());
            System.out.printf("   * Variant 3: %s%n", quiz.getAnswer3());
            System.out.println();
        }
    }
    @Override
    public List<Quiz> getQuestionList(){
        return dao.getAll();
    }
}
