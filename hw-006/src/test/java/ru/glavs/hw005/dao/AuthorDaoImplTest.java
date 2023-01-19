package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Author;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(AuthorDaoImpl.class)
@DisplayName("Класс AuthorDao")
class AuthorDaoImplTest {

    private static final long ALL_AUTHORS_NUMBER = 2;
    private static final int FIRST_AUTHOR_ID = 1;
    private static final Author FIRST_AUTHOR = new Author(1, "Имя1", "Фамилия1", "А.А.");
    @Autowired
    private EntityManager em;
    @Autowired
    private AuthorDaoImpl dao;

    @Test
    @DisplayName("должен возвращать автора по его id")
    void getByIdMethodShouldReturnAuthorById() {
        Author author = dao.getById(FIRST_AUTHOR_ID);
        assertThat(author).usingRecursiveComparison().isEqualTo(FIRST_AUTHOR);
    }

    @Test
    void getAll() {
    }

    @Test
    @DisplayName("должен посчитать количество всех авторов")
    void countMethodShouldReturnAuthorsQuantity() {
        Long count = dao.count();
        assertThat(count).isEqualTo(ALL_AUTHORS_NUMBER);
    }

    @Test
    void insertNew() {
    }

    @Test
    void delete() {
    }

    @Test
    void searchBySurname() {
    }
}