package ru.otus.glavs.service.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.loader.Loader;
import ru.otus.glavs.service.loader.TextFileLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CsvFileParser class")
class CsvFileParserTest {
    private List<Quiz> quizList;

    @BeforeEach
    void init() {
        String filename = "testquiz.csv";
        Loader loader = new TextFileLoader(filename);
        Parser fileParser = new CsvFileParser(loader);
        quizList = fileParser.parse();
    }

    @Test
    @DisplayName("Parse method should return List<Quiz>")
    void parseMethodShouldReturnCorrectList() {
        assertThat(quizList).isInstanceOf(List.class);
        assertThat(quizList.get(0)).isInstanceOf(Quiz.class);
    }

    @Test
    @DisplayName("Parser should correctly parse resource csv file")
    void parserShouldCorrectlyParseCsvFile() {
        assertThat(quizList.get(0).getQuestion()).isEqualTo("Q1");
        assertThat(quizList.get(1).getAnswer3()).isEqualTo("A3");
    }
}