package ru.glavs.hw005.service.CRUD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.glavs.hw005.dao.BookDaoImpl;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.StreamIOService;
import ru.glavs.hw005.service.display.BookDisplayService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("В классе BookCRUDService")
class BookCRUDServiceTest {

    @Autowired
    private AuthorCRUDService authorOps;
    @Autowired
    private GenreCRUDService genreOps;
    @MockBean
    private StreamIOService ioService;
    @Autowired
    private BookDisplayService bookDisplayService;
    @Autowired
    private BookCRUDService bookCRUDService;
    @MockBean
    private BookDaoImpl bookDao;
    private final List<Book> testBookList = List.of(new Book(1,
                    new Author(1, "Имя1", "Фамилия1", "А.А."),
                    new Genre(1, "Жанр1"), "Книга1"),
            new Book(2,
                    new Author(2, "Имя2", "Фамилия2", "Б.Б."),
                    new Genre(2, "Жанр2"), "Книга1"));


    @BeforeEach
    void init() {

    }


    @Test
    @DisplayName("метод delete должен вызывать соответствующий метод DAO по решению пользователя")
    void deleteMethodShouldInvokeCorrespondingDAOMethodByUserDecision() {
        when(bookDao.getById(1)).thenReturn(testBookList.get(0));
        when(ioService.readStringWithPrompt(anyString())).thenReturn("y");
        bookCRUDService.delete(1);
        verify(bookDao, times(1)).delete(1);


    }
    @Test
    @DisplayName("метод delete НЕ должен вызывать соответствующий метод DAO по решению пользователя")
    void deleteMethodShouldNotInvokeCorrespondingDAOMethodByUserDecision() {
        when(bookDao.getById(1)).thenReturn(testBookList.get(0));
        when(ioService.readStringWithPrompt(anyString())).thenReturn("n");
        bookCRUDService.delete(1);
        verify(bookDao, never()).delete(1);
    }

    @Test
    void create() {
    }

    @Test
    void readAll() {
    }

    @Test
    void readBook() {
    }

    @Test
    void update() {
    }
}