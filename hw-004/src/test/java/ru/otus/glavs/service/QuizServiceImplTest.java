package ru.otus.glavs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.glavs.dao.QuizDaoCsvImpl;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.l10n.LocalizedMessagesProvider;
import ru.otus.glavs.service.ioservice.StreamIOService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("В классе QuizServiceImpl")
class QuizServiceImplTest {
    @MockBean
    private QuizDaoCsvImpl dao;
    @MockBean
    private StreamIOService ioService;
    @MockBean
    private LocalizedMessagesProvider messagesProvider;

    @Autowired
    private QuizServiceImpl quizService;

    private final List<Quiz> quizList = new ArrayList<>();

    @BeforeEach
    void init() {
        quizList.add(new Quiz(0, "question1", "ans1", "ans2", "ans3", 2));
        quizList.add(new Quiz(1, "question2", "ans1", "ans2", "ans3", 1));
    }

    @Test
    @DisplayName("метод displayAllQuestions должен вызывать метод dao getAll")
    void displayAllQuestionsShouldCallGetAllMethod() {
        quizService.displayAllQuestions();
        verify(dao, times(1)).getAll();
    }

    @Test
    @DisplayName("метод displayQuestion должен вызывать методы ioService")
    void displayQuestionMethodShouldCallIOServiceCorrectly() {
        when(messagesProvider.getTextMessage(anyString())).thenReturn("someMessage");
        quizService.displayQuestion(quizList.get(1));
        verify(ioService, times(5)).write(anyString());
    }

    @Test
    @DisplayName("метод getQuestionList должен вызывать метод dao getAll()")
    void getQuestionListShoudCallCorrectDAOMethod() {
        quizService.getQuestionList();
        verify(dao, times(1)).getAll();
    }

    @Test
    @DisplayName("метод getQuestionById должен вызывать метод dao getById()")
    void getQuestionByIdShouldCallGetByIdDaoMethod() {
        int id = 1;
        quizService.getQuestionById(id);
        verify(dao, times(1)).getById(id);
    }

    @Test
    @DisplayName("метод getAnswerByNumber должен возвращать строку")
    void getAnswerByNumberShouldReturnString() {
        Quiz quiz = quizList.get(1);
        assertThat(quizService.getAnswerByNumber(quiz, 2)).isInstanceOf(String.class);
    }

    @Test
    @DisplayName("метод getAnswerByNumber должен возвращать верную строку по id")
    void getAnswerByNumberShouldReturnCorrectStringById() {
        Quiz quiz = quizList.get(1);
        assertThat(quizService.getAnswerByNumber(quiz, 2)).isEqualTo("ans2");
    }

    @Configuration
    @ComponentScan({"ru.otus.glavs.dao", "ru.otus.glavs.domain", "ru.otus.glavs.l10n", "ru.otus.glavs.service"})
    public static class NestedConf {

    }
}