package ru.glavs.hw013.service.CRUD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.glavs.hw013.dao.AuthorDao;
import ru.glavs.hw013.dao.BookDao;
import ru.glavs.hw013.dao.GenreDao;
import ru.glavs.hw013.domain.Book;
import ru.glavs.hw013.domain.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Класс BookCRUDImpl должен")
class BookCRUDImplTest {

    @Autowired
    private BookCRUDImpl bookCRUD;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;


    private final Book mockBook = mock(Book.class);
    private final List<Book> bookList = new ArrayList<>();

    @BeforeEach
    void prepare() {
        when(mockBook.getComments()).thenReturn(List.of(new Comment()));
        bookList.add(mockBook);
    }

    @Test
    @DisplayName("сохраняя книгу в БД, вызывать нужные методы DAO")
    void saveMethodShouldCallExpectedDaoMethodsSavingBook() {
        bookCRUD.save(mockBook);
        verify(bookDao, times(1)).save(mockBook);
        verify(authorDao, times(1)).save(mockBook.getAuthor());
        verify(genreDao, times(1)).save(mockBook.getGenre());
    }

    @Test
    @DisplayName("возвратить список книг и вызвать нужный метод DAO")
    void readAllShouldReturnExpectedBookListAndCallCorrectDaoMethod() {
        when(bookDao.findAll()).thenReturn(bookList);
        assertThat(bookCRUD.findAll()).isInstanceOf(List.class);
        verify(bookDao, times(1)).findAll();
        assertThat(bookCRUD.findAll().get(0)).isInstanceOf(Book.class);
        verify(bookDao, times(2)).findAll();
    }

    @Test
    @DisplayName("возвратить книгу и вызвать нужный метод DAO")
    void readBookShouldReturnExpectedBookAndCallCorrectDaoMethod() {
        when(bookDao.getReferenceById(anyLong())).thenReturn(mockBook);
        assertThat(bookCRUD.findById(anyLong())).isInstanceOf(Book.class);
        verify(bookDao, times(1)).getReferenceById(anyLong());
    }

    @Test
    @DisplayName("удалить книгу и вызвать нужный метод DAO")
    void deleteByIdShouldDeleteExpectedBookAndCallCorrectDaoMethod() {
        when(bookDao.getReferenceById(anyLong())).thenReturn(mockBook);
        bookCRUD.deleteById(anyLong());
        verify(bookDao, times(1)).getReferenceById(anyLong());
        verify(bookDao, times(1)).delete(mockBook);
    }

    @Configuration
    @Import({BookCRUDImpl.class,
            BookDao.class,
            AuthorDao.class,
            GenreDao.class})
    public static class ConfigForTest {

    }

}