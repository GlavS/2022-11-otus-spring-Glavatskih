package ru.otus.glavs.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@DisplayName("In QuizDaoCsvImpl class")
@ExtendWith(MockitoExtension.class)
class QuizDaoImplTest {
    @Mock
    Parser parserMock;
    List<Quiz> testList = new ArrayList<>();
    QuizDao dao;

    @BeforeEach
    void init() {
        testList.add(new Quiz(1, "Q", "A1", "A2", "A3", 2));
        testList.add(new Quiz(2, "Q2", "A4", "A5", "A6", 1));
        assertNotNull(parserMock);
        when(parserMock.parse()).thenReturn(testList);
        this.dao = new QuizDaoCsvImpl(parserMock);
    }

    @Test
    @DisplayName("getAll method should return List<Quiz>")
    void getAllShouldReturnCorrectList() {
        assertAll(
                () -> assertThat(dao.getAll()).isInstanceOf(List.class),
                () -> assertThat(dao.getAll().get(0)).isInstanceOf(Quiz.class)
        );
    }

    @Test
    @DisplayName("getById method should return null if no such Quiz in database")
    void getByIdMethodShouldReturnNullIfNoSuchQuiz() {
        assertThat(dao.getById(3)).isNull();
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("getById method should return Quiz instance by valid id")
    void getByIdMethodShouldReturnQuizIfIdIsValid(int id) {
        assertThat(dao.getById(id)).isInstanceOf(Quiz.class);
    }
}