package ru.otus.glavs.dao;

import ru.otus.glavs.dao.parser.Parser;
import ru.otus.glavs.domain.Quiz;

import java.util.List;

@SuppressWarnings("unused")
public class QuizDaoCsvImpl implements QuizDao {
    private Parser parser;

    public QuizDaoCsvImpl(Parser parser) {
        this.parser = parser;
    }

    @Override
    public List<Quiz> getAll() {
        return parser.parse();
    }
}
