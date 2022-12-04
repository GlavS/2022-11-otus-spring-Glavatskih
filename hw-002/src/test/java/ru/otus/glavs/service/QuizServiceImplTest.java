package ru.otus.glavs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("QuizServiceImpl class")
class QuizServiceImplTest {

    private final QuizDao dao = mock(QuizDao.class);

    private final ConsoleHelper ch = mock(ConsoleHelper.class);
    private QuizService quizService;

    @BeforeEach
    void init() {
        quizService = new QuizServiceImpl(dao, ch);
    }

    @Test
    @DisplayName("Constructor is working correctly")
    void displayQuestionCorrectlyCreatedByConstructor() {
        assertThat(quizService).isInstanceOf(QuizServiceImpl.class);
    }

    @Test
    @DisplayName("getQuestionList method returns List<Quiz>")
    void getQuestionListTest() {
        List<Quiz> inputList = new ArrayList<>();
        inputList.add(new Quiz(1, "Q", "A1", "A2", "A3", 1));

        when(dao.getAll()).thenReturn(inputList);

        List<Quiz> quizList = quizService.getQuestionList();
        assertThat(quizList).isInstanceOf(List.class);
        assertThat(quizList.get(0)).isInstanceOf(Quiz.class);
    }

}