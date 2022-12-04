package ru.otus.glavs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.service.helper.ConsoleHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DisplayName("QuizServiceImpl class")
class QuizServiceImplTest {
    QuizDao dao = mock(QuizDao.class);
    ConsoleHelper ch = mock(ConsoleHelper.class);

    @Test
    @DisplayName("Constructor is working correctly")
    void displayQuestionCorrectlyCreatedByConstructor() {
        assertThat(new QuizServiceImpl(dao, ch)).isInstanceOf(QuizServiceImpl.class);
    }

    @Test
    void getQuestionList() {
    }
}