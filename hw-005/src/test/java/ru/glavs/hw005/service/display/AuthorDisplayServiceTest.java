package ru.glavs.hw005.service.display;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.dao.AuthorDao;
import ru.glavs.hw005.dao.AuthorDaoImpl;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.io.StreamIOService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("В классе AuthorDisplayService")
class AuthorDisplayServiceTest {
    private final List<Author> testList = List.of(
            new Author(1, "Имя1", "Фамилия1", "А.А."),
            new Author(2, "Имя2", "Фамилия2", "Б.Б.")
    );
    @MockBean
    StreamIOService ioService;
    @Autowired
    private AuthorDisplayService service;


    @Test
    @DisplayName("метод printOne корректно вызывает методы интерфейса IOService")
    void printOneMethodShouldCorrectlyInvokeIOServiceMethods() {
        service.printOne(testList.get(0));
        verify(ioService, times(3)).println(anyString());
        verify(ioService, times(2)).printf(anyString(), any());
    }

    @Test
    @DisplayName("метод printList корректно вызывает методы интерфейса IOService")
    void printListMethodShouldCorrectlyInvokeIOServiceMethods() {
        service.printList(testList);
        verify(ioService, times(3)).println(anyString());
        verify(ioService, times(3)).printf(anyString(), any());
    }

    @Test
    @DisplayName("метод displayItem корректно вызывает методы интерфейса IOService")
    void displayItemMethodShouldCorrectlyInvokeIOServiceMethods() {
        service.displayItem(testList.get(0));
        verify(ioService, times(1)).printf(anyString(), any());
    }
    @Configuration
    @ComponentScan({"ru.glavs.hw005.service.display", "ru.glavs.hw005.io"})
    static class TestConfig{

    }
}