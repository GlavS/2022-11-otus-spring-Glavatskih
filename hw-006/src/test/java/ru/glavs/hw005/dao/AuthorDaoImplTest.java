package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Author;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(AuthorDaoImpl.class)
@DisplayName("Класс AuthorDao")
class AuthorDaoImplTest {

    private static final long ALL_AUTHORS_NUMBER = 2;
    private static final int FIRST_AUTHOR_ID = 1;
    private static final Author FIRST_AUTHOR = new Author(1, "Имя1", "Фамилия1", "А.А.");
    private static final Author SECOND_AUTHOR = new Author(2, "Имя2", "Фамилия2", "Б.Б.");
    private static final Author NEW_AUTHOR = new Author(0, "Имя3", "Фамилия3", "В.В.");
    private static final Author THIRD_AUTHOR = new Author(3, "Имя3", "Фамилия3", "В.В.");
    private static final List<Author> AUTHOR_LIST = List.of(FIRST_AUTHOR, SECOND_AUTHOR);
    private static final String SECOND_AUTHOR_SURNAME = "Фамилия2";
    @Autowired
    private AuthorDaoImpl dao;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("должен возвращать автора по его id")
    void getByIdMethodShouldReturnAuthorById() {
        Author author = dao.getById(FIRST_AUTHOR_ID);
        assertThat(author).usingRecursiveComparison().isEqualTo(FIRST_AUTHOR);
    }

    @Test
    @DisplayName("должен возвращать список всех авторов")
    void getAllMethodShouldReturnCorrectAuthorsList() {
        List<Author> authorList = dao.getAll();
        assertThat(authorList).usingRecursiveComparison().isEqualTo(AUTHOR_LIST);
    }

    @Test
    @DisplayName("должен посчитать количество всех авторов")
    void countMethodShouldReturnAuthorsQuantity() {
        long count = dao.count();
        assertThat(count).isEqualTo(ALL_AUTHORS_NUMBER);
    }

    @Test
    @DisplayName("должен добавлять в БД нового автора")
    void saveMethodShouldAddNewAuthorToDatabase() {
        dao.save(NEW_AUTHOR);
        List<Author> currentAuthorList = new ArrayList<>();
        for (int i = 1; i <= ALL_AUTHORS_NUMBER + 1; i++) {
            currentAuthorList.add(em.find(Author.class, i));
        }
        assertThat(currentAuthorList).hasSize(3).containsExactlyInAnyOrder(FIRST_AUTHOR, SECOND_AUTHOR, THIRD_AUTHOR);
    }

    @Test
    @DisplayName("должен удалять автора с указанным id")
    void deleteShouldDeleteAuthorByHisID() {
        dao.delete(FIRST_AUTHOR_ID);
        em.flush();
        List<Author> afterDeleteList = List.of(em.find(Author.class, 2));
        assertThat(afterDeleteList).usingRecursiveComparison().isEqualTo(List.of(SECOND_AUTHOR));
    }

    @Test
    @DisplayName("должен искать и возвращать список авторов по фамилии")
    void searchBySurnameShouldFindAuthorByHisSurname() {
        List<Author> authorList = dao.searchBySurname(SECOND_AUTHOR_SURNAME);
        assertThat(authorList).usingRecursiveComparison().isEqualTo(List.of(SECOND_AUTHOR));
    }
}