package ru.otus.glavs.service.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.loader.Loader;
import ru.otus.glavs.service.loader.TextFileLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("CsvFileParser class")
@ExtendWith(MockitoExtension.class)
class CsvFileParserTest {
    private List<Quiz> quizList;
    @Mock
    private LocalizedMessages storage;

    @BeforeEach
    void init() {
        String filename = "testquiz.csv";
        when(storage.getText(anyString())).thenReturn(filename);
        Loader loader = new TextFileLoader(storage);
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