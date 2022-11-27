package ru.otus.glavs.dao.parser;

import ru.otus.glavs.domain.Quiz;

import java.util.List;

public interface Parser {
    List<Quiz> parse();
}
