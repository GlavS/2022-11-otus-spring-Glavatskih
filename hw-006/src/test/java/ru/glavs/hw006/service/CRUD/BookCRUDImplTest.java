package ru.glavs.hw006.service.CRUD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.glavs.hw006.dao.AuthorDaoImpl;
import ru.glavs.hw006.dao.BookDaoImpl;
import ru.glavs.hw006.dao.GenreDaoImpl;
import ru.glavs.hw006.domain.Book;
import ru.glavs.hw006.domain.Comment;

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
    private BookDaoImpl bookDao;

    @MockBean
    private AuthorDaoImpl authorDao;

    @MockBean
    private GenreDaoImpl genreDao;


    private final Book mockBook = mock(Book.class);
    private final List<Book> bookList = new ArrayList<>();

    @BeforeEach
    void prepare(){
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
        when(bookDao.getAll()).thenReturn(bookList);
        assertThat(bookCRUD.readAll()).isInstanceOf(List.class);
        verify(bookDao, times(1)).getAll();
        assertThat(bookCRUD.readAll().get(0)).isInstanceOf(Book.class);
        verify(bookDao, times(2)).getAll();
    }

    @Test
    @DisplayName("возвратить книгу и вызвать нужный метод DAO")
    void readBookShouldReturnExpectedBookAndCallCorrectDaoMethod() {
        when(bookDao.getById(anyLong())).thenReturn(mockBook);
        assertThat(bookCRUD.readBook(anyLong())).isInstanceOf(Book.class);
        verify(bookDao, times(1)).getById(anyLong());
    }

    @Test
    @DisplayName("удалить книгу и вызвать нужный метод DAO")
    void deleteByIdShouldDeleteExpectedBookAndCallCorrectDaoMethod() {
        when(bookDao.getById(anyLong())).thenReturn(mockBook);
        bookCRUD.deleteById(anyLong());
        verify(bookDao, times(1)).getById(anyLong());
        verify(bookDao, times(1)).delete(mockBook);
    }

    @Test
    @DisplayName("получить список книг с комментариями, вызвав нужный метод DAO")
    void readAllWithCommentsOnlyShouldReturnExpectedBookListAndCallCorrectDaoMethod() {
        when(bookDao.getAllWithCommentsOnly()).thenReturn(bookList);
        assertThat(bookCRUD.readAllWithCommentsOnly()).isInstanceOf(List.class);
        verify(bookDao, times(1)).getAllWithCommentsOnly();
        assertThat(bookCRUD.readAllWithCommentsOnly().get(0)).isInstanceOf(Book.class);
        verify(bookDao, times(2)).getAllWithCommentsOnly();
    }

    @Configuration
    @Import({BookCRUDImpl.class,
            BookDaoImpl.class,
            AuthorDaoImpl.class,
            GenreDaoImpl.class})
    public static class ConfigForTest {

    }

}