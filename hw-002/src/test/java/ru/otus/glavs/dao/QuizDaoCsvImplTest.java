package ru.otus.glavs.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.service.parser.Parser;
import ru.otus.glavs.domain.Quiz;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("In QuizDaoCsvImpl class")
@ExtendWith(MockitoExtension.class)
class QuizDaoImplTest {
    @Mock
    Parser parserMock;

    @Test
    @DisplayName("getAll method should return List<Quiz>")
    void getAllShouldReturnCorrectList() {
        List<Quiz> testList = new ArrayList<>();
        testList.add(new Quiz(1, "Q", "A1", "A2", "A3", 2));
        testList.add(new Quiz(2, "Q2", "A4", "A5", "A6", 1));
        assertNotNull(parserMock);
        when(parserMock.parse()).thenReturn(testList);

        QuizDao dao = new QuizDaoCsvImpl(parserMock);

        assertAll(
                () -> assertThat(dao.getAll()).isInstanceOf(List.class),
                () -> assertThat(dao.getAll().get(0)).isInstanceOf(Quiz.class)
        );
    }
}