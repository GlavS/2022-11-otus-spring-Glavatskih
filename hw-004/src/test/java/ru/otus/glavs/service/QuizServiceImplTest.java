package ru.otus.glavs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.glavs.dao.QuizDao;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class QuizServiceImplTest {

    @MockBean
    private QuizDao dao;
    @Test
    @DisplayName("displayAllQuestions method should call dao.getAll() method")
    void displayAllQuestionsMethodShouldCallDaoGetAllMethod() {

    }

    @Test
    void displayQuestion() {
    }

    @Test
    void getQuestionList() {
    }

    @Test
    void getQuestionById() {
    }

    @Test
    void getAnswerByNumber() {
    }
}