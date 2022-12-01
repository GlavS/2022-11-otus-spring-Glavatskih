package ru.otus.glavs.dao;

import ru.otus.glavs.dao.parser.Parser;
import ru.otus.glavs.domain.Quiz;

import java.util.List;

public class QuizDaoCsvImpl implements QuizDao {
    private final Parser csvParser;

    public QuizDaoCsvImpl(Parser parser) {
        this.csvParser = parser;
    }

    @Override
    public List<Quiz> getAll() {
        return csvParser.parse();
    }
}
