package ru.otus.glavs.dao;

import ru.otus.glavs.domain.Quiz;

import java.util.List;

@SuppressWarnings("unused")
public class QuizDaoCsvImpl implements QuizDao {
    private final CsvReader reader;

    public QuizDaoCsvImpl(CsvReader reader) {
        this.reader = reader;
    }

    @Override
    public List<Quiz> getAll() {
        return reader.readAllRows();
    }
}
