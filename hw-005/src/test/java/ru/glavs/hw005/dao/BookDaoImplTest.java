package ru.glavs.hw005.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

@JdbcTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class})
@DisplayName("В классе BookDaoImpl")
class BookDaoImplTest {

    @Autowired
    private BookDaoImpl bookDao;
    private Book testBook;
    private static final int TEST_RECORDS_COUNT = 4;

    @BeforeEach
    void init() {
        this.testBook = new Book(2,
                new Author(1, "Имя5", "Фамилия5", "Д.Д."),
                new Genre(1, "Жанр5"), "Книга5");
    }

    @Test
    @DisplayName("метод getAll должен вернуть все книги")
    void getAllMethodShouldReturnAllBooks() {
        assertThat(bookDao.getAll()).isInstanceOf(List.class);
        assertThat(bookDao.getAll().size()).isEqualTo(TEST_RECORDS_COUNT);
        assertThat(bookDao.getAll().get(0)).isInstanceOf(Book.class);
    }

    @Test
    @DisplayName("метод getById должен возвратить книгу по её id")
    void getByIdMethodShouldReturnExpectedBookByItsId() {
        assertThat(bookDao.getById(TEST_RECORDS_COUNT).getAuthor().getSurname()).isEqualTo("Фамилия2");
    }

    @Test
    @DisplayName("метод insertNew должен вернуть корректный сгенерированный keyHolder key")
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void insertNewMethodShouldReturnCorrectGeneratedKey() {
        assertThat(bookDao.insertNew(testBook)).isEqualTo(TEST_RECORDS_COUNT + 1);
    }

    @Test
    @DisplayName("метод insertNew должен корректно сохранять новую книгу")
    void insertNewMethodShouldSaveCorrectBookToDb() {
        int key = bookDao.insertNew(testBook);
        assertThat(bookDao.getById(key).getAuthor().getName()).isEqualTo("Имя5");
    }

    @Test
    void update() {

    }


    @Test
    void delete() {
    }
}