package ru.glavs.hw005.service.CRUD;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("В классе BookCRUDService")
class BookCRUDServiceTest {

    @MockBean
    private AuthorCRUDService authorOps;
    @MockBean
    private GenreCRUDService genreOps;
    @MockBean
    private StreamIOService ioService;
    @MockBean
    private BookDisplayService bookDisplayService;
    @MockBean
    private BookDaoImpl bookDao;

    @Autowired
    private BookCRUDService bookCRUDService;

    private final List<Book> testBookList = List.of(new Book(1,
                    new Author(1, "Имя1", "Фамилия1", "А.А."),
                    new Genre(1, "Жанр1"), "Книга1"),
            new Book(2,
                    new Author(2, "Имя2", "Фамилия2", "Б.Б."),
                    new Genre(2, "Жанр2"), "Книга1"));

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
    @DisplayName("метод create должен корректно создать и вернуть книгу, выведя ее на экран")
    void createMethodShouldCreateAndDisplayNewBook() {
        Author newAuthor = testBookList.get(0).getAuthor();
        Genre newGenre = testBookList.get(0).getGenre();

        when(authorOps.getAuthorBySurname(any())).thenReturn(newAuthor);
        when(genreOps.getGenreByName(any())).thenReturn(newGenre);
        when(ioService.readStringWithPrompt("Please enter title:")).thenReturn("Книга1");
        when(bookDao.insertNew(any(Author.class), any(Genre.class), anyString())).thenReturn(1);

        Book createdBook = bookCRUDService.create();

        assertThat(createdBook).usingRecursiveComparison().isEqualTo(testBookList.get(0));
        verify(bookDisplayService).printOne(any(Book.class));
    }

    @Test
    @DisplayName("метод readAll должен вызывать методы bookDisplayService и DAO")
    void readAllShouldInvokeCorrectMethodsOfDisplayServiceAndDAO() {
        bookCRUDService.readAll();
        verify(bookDao, times(1)).getAll();
        verify(bookDisplayService).printList(anyList());
    }

    @Test
    @DisplayName("метод readBook должен достать из БД книгу и вывести ее на экран")
    void readBookMethodShouldGetAndDisplayBook() {
        int id = 1;
        Book book = testBookList.get(0);
        when(bookDao.getById(id)).thenReturn(book);
        bookCRUDService.readBook(id);
        verify(bookDao).getById(id);
        verify(bookDisplayService).printOne(book);
    }

    @Test
    @DisplayName("метод update должен обновить книгу и вывести ее на экран")
    void updateMethodShouldCorrectlyUpdateAndDisplayBook() {
        Book updatingBook = testBookList.get(0);
        when(bookDao.getById(anyInt())).thenReturn(updatingBook);
        when(authorOps.getAuthorForUpdate(any())).thenReturn(updatingBook.getAuthor());
        when(genreOps.getGenreForUpdate(any())).thenReturn(updatingBook.getGenre());
        when(ioService.readStringWithPrompt("Please enter new title, or ENTER to skip:")).thenReturn("NewTitle");

        bookCRUDService.update();
        verify(bookDao).update(any());
        verify(bookDisplayService, atLeast(2)).printOne(any());

    }
}