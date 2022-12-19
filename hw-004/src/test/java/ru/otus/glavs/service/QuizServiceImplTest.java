package ru.otus.glavs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.helper.ConsoleHelperImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("QuizServiceImpl class")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {
    @Mock
    private QuizDao dao;
    @Mock
    private ConsoleHelperImpl ch;
    @Mock
    private LocalizedMessages storage;
    private QuizService quizService;

    @BeforeEach
    void init() {
        quizService = new QuizServiceImpl(dao, ch, storage);
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