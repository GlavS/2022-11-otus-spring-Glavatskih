package ru.glavs.hw005.dao;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

@JdbcTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
@DisplayName("В классе BookDaoImpl")
class BookDaoImplTest {

    private static final int TEST_BOOKS_COUNT = 4;
    @Autowired
    private BookDaoImpl bookDao;

    private Book newBook;

    @BeforeEach
    void init() {
        this.newBook = new Book(2,
                new Author(2, "Имя2", "Фамилия2", "Б.Б."),
                new Genre(2, "Жанр2"), "Книга5");
    }

    @Test
    @DisplayName("метод getAll должен вернуть все книги")
    void getAllMethodShouldReturnAllBooks() {
        assertThat(bookDao.getAll()).isInstanceOf(List.class);
        assertThat(bookDao.getAll().size()).isEqualTo(TEST_BOOKS_COUNT);
        assertThat(bookDao.getAll().get(0)).isInstanceOf(Book.class);
    }

    @Test
    @DisplayName("метод getById должен возвратить книгу по её id")
    void getByIdMethodShouldReturnExpectedBookByItsId() {
        assertThat(bookDao.getById(TEST_BOOKS_COUNT).getAuthor().getSurname()).isEqualTo("Фамилия2");
    }

    @Test
    @DisplayName("метод insertNew должен вернуть корректный сгенерированный keyHolder key")
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void insertNewMethodShouldReturnCorrectGeneratedKey() {
        assertThat(bookDao.insertNew(newBook.getAuthor(), newBook.getGenre(), newBook.getTitle())).isEqualTo(TEST_BOOKS_COUNT + 1);
    }

    @Test
    @DisplayName("метод insertNew должен корректно сохранять новую книгу")
    void insertNewMethodShouldSaveCorrectBookToDb() {
        int key = bookDao.insertNew(newBook.getAuthor(), newBook.getGenre(), newBook.getTitle());
        assertThat(bookDao.getById(key).getTitle()).isEqualTo("Книга5");
    }

    @Test
    @DisplayName("метод update должен обновлять указанную книгу")
    void updateMethodShouldCorrectlyUpdateExpectedBook() {
        Book updatedBook = new Book(
                1, new Author(2, "Имя2", "Фамилия2","Б.Б."),
                new Genre(2, "Жанр2"), "Новое название");
        bookDao.update(updatedBook);
        assertThat(bookDao.getById(1).getTitle()).isEqualTo("Новое название");
    }


    @Test
    @DisplayName("метод delete должен удалять выбранную книгу из БД")
    void deleteMethodShouldDeleteCorrectBookFromDatabase() {
        int idForDelete = 1;
        bookDao.delete(idForDelete);
        SoftAssertions bookBundle = new SoftAssertions();
        bookBundle
                .assertThatThrownBy(() -> bookDao.getById(idForDelete)).isInstanceOf(EmptyResultDataAccessException.class);
        bookBundle
                .assertThat(bookDao.count()).isEqualTo(TEST_BOOKS_COUNT - 1);
        bookBundle.assertAll();
    }

    @Test
    @DisplayName("метод count должен вернуть общее количество книг")
    void countMethodShouldReturnNumberOfBooksInDb(){
        assertThat(bookDao.count()).isEqualTo(TEST_BOOKS_COUNT);
    }
}