package ru.otus.glavs.dao;

import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.parser.Parser;

import java.util.ArrayList;
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

    @Override
    public Quiz getById(int id) {
        List<Quiz> quizList = new ArrayList<>();
        quizList = getAll();
        Quiz result = quizList.stream().filter((quiz) -> quiz.getId() == id).findFirst().orElse(null);
        return result;
    }
}
