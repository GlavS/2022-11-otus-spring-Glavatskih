package ru.glavs.hw005.dao;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.glavs.hw005.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoImpl.class)
@DisplayName("В дао AuthorDao")
class AuthorDaoImplTest {

    private static final int TEST_AUTHORS_COUNT = 2;
    private Author newAuthor;
    private Author expectedAuthor;
    @Autowired
    private AuthorDaoImpl authorDao;
    @BeforeEach
    void init(){
         this.newAuthor = new Author(3, "Имя3", "Фамилия3", "В.В.");
         this.expectedAuthor = new Author(1, "Имя1", "Фамилия1", "А.А.");
    }

    @Test
    @DisplayName("метод getById должен вернуть автора по его id")
    void getByIdMethodShouldReturnCorrectAuthorById() {
        assertThat(authorDao.getById(1)).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("метод getAll должен вернуть список авторов")
    void getAllMethodShouldReturnExpectedAuthorsList() {
        assertThat(authorDao.getAll().size()).isEqualTo(2);
        assertThat(authorDao.getAll().get(1)).isInstanceOf(Author.class);
    }

    @Test
    @DisplayName("метод count должен возвращать количество авторов, сохраненных в БД")
    void countMethodShouldReturnCorrectNumberOfStoredAuthors() {
        assertThat(authorDao.count()).isEqualTo(TEST_AUTHORS_COUNT);
    }

    @Test
    @DisplayName("метод insertNew должен сохранять в БД нового автора")
    void insertNewMethodShouldSaveNewAuthorToDatabase() {
        int key = authorDao.insertNew(new Author(3, "Имя3", "Фамилия3", "В.В."));
        SoftAssertions insertBundle = new SoftAssertions();
        insertBundle.assertThat(key).isEqualTo(TEST_AUTHORS_COUNT + 1);
        insertBundle.assertThat(authorDao.getById(key)).isInstanceOf(Author.class);
        insertBundle.assertThat(authorDao.getById(key).getSurname()).isEqualTo("Фамилия3");
        insertBundle.assertAll();
    }

    @Test
    @DisplayName("метод delete должен удалять выбранного автора из БД")
    void deleteMethodShouldCorrectlyDeleteAuthorFromDb() {
        authorDao.delete(expectedAuthor);
        SoftAssertions deleteBundle = new SoftAssertions();
        deleteBundle.assertThatThrownBy(() -> authorDao.getById(1)).isInstanceOf(EmptyResultDataAccessException.class);
        deleteBundle.assertThat(authorDao.count()).isEqualTo(TEST_AUTHORS_COUNT - 1);
        deleteBundle.assertAll();
    }
}