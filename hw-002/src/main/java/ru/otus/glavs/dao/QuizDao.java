package ru.otus.glavs.dao;

import ru.otus.glavs.domain.Quiz;

import java.util.List;

public interface QuizDao {
    List<Quiz> getAll();

    Quiz getById(int id);
}
