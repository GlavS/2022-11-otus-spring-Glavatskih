package ru.otus.glavs.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.parser.CsvFileParser;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("В классе QuizDaoCsvImplTest")
class QuizDaoCsvImplTest {

    @MockBean
    private CsvFileParser csvFileParser;

    @Autowired
    private QuizDaoCsvImpl dao;
    private final List<Quiz> quizList = new ArrayList<>();

    @BeforeEach
    void init() {
        quizList.add(new Quiz(0, "question1", "ans1", "ans2", "ans3", 2));
        quizList.add(new Quiz(1, "question2", "ans1", "ans2", "ans3", 1));
        when(csvFileParser.parse()).thenReturn(quizList);
    }

    @Test
    @DisplayName("метод getAll должен возвращать список объектов Quiz")
    void getAllShouldReturnQuizList() {
        assertThat(dao.getAll()).usingRecursiveComparison().isEqualTo(quizList);
    }

    @Test
    @DisplayName("метод getById должен возвратить объект Quiz")
    void getByIdShouldReturnQuizObject() {
        assertThat(dao.getById(1)).usingRecursiveComparison().isEqualTo(quizList.get(1));
    }

    @Configuration
    @ComponentScan({"ru.otus.glavs.dao", "ru.otus.glavs.service.parser"})
    public static class NestedConf {

    }
}