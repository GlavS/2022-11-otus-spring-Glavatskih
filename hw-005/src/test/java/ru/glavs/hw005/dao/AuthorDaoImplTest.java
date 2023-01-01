package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoImpl.class)
@DisplayName("В дао AuthorDao")
class AuthorDaoImplTest {

    @Autowired
    private AuthorDaoImpl authorDao;

    @Test
    @DisplayName("метод getById должен вернуть автора по его id")
    void getByIdMethodShouldReturnCorrectAuthorById() {
        Author expectedAuthor = new Author(1, "Имя1", "Фамилия1", "А.А.");
        assertThat(authorDao.getById(1)).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("метод getAll должен вернуть список авторов")
    void getAllMethodShouldReturnExpectedAuthorsList() {
        assertThat(authorDao.getAll().size()).isEqualTo(2);
        assertThat(authorDao.getAll().get(1)).isInstanceOf(Author.class);
    }
}