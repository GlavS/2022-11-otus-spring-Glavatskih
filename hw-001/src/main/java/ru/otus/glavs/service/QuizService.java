package ru.otus.glavs.service;

import ru.otus.glavs.dao.QuizDao;

public class QuizService {
    private QuizDao dao;

    public QuizService(QuizDao dao) {
        this.dao = dao;
    }

    public void displayAll(){

    }
}
