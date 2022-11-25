package ru.otus.glavs.dao.parser;

import ru.otus.glavs.dao.loader.Loader;
import ru.otus.glavs.domain.Quiz;

import java.util.List;

public class CsvParser implements Parser{
    private Loader loader;
    @Override
    public List<Quiz> parse() {
        return null;
    }

    public CsvParser(Loader loader) {
        this.loader = loader;
    }
}
