package ru.otus.glavs.dao;

import ru.otus.glavs.domain.Quiz;

import java.util.List;

@SuppressWarnings("unused")
public interface QuizDao {
    List<Quiz> getAll();
}
