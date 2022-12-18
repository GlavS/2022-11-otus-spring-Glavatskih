package ru.otus.glavs.dao;

import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.parser.Parser;

import java.util.List;

@Component
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
        List<Quiz> quizList;
        quizList = getAll();
        return quizList.stream()
                .filter((quiz) -> quiz.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
