package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(AuthorDaoImpl.class)
@DisplayName("Класс AuthorDao")
class AuthorDaoImplTest {

    private static final long ALL_AUTHORS_NUMBER = 2;
    private static final int FIRST_AUTHOR_ID = 1;
    private static final Author NEW_AUTHOR = new Author("Имя3", "Фамилия3", "В.В.");
    private static final String SECOND_AUTHOR_SURNAME = "Фамилия2";
    @Autowired
    private AuthorDaoImpl dao;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("должен возвращать автора по его id")
    void getByIdMethodShouldReturnAuthorById() {
        Author author = dao.getById(FIRST_AUTHOR_ID);
        assertThat(author.getSurname()).isEqualTo("Фамилия1");
    }

    @Test
    @DisplayName("должен возвращать список всех авторов")
    void getAllMethodShouldReturnCorrectAuthorsList() {
        List<Author> authorList = dao.getAll();
        assertThat(authorList.size()).isEqualTo(2);
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
        assertThat(currentAuthorList).hasSize(3);
        assertThat(currentAuthorList.get(2).getSurname()).isEqualTo("Фамилия3");
    }

    @Test
    @DisplayName("должен удалять автора с указанным id")
    void deleteShouldDeleteAuthorByHisID() {
        dao.delete(FIRST_AUTHOR_ID);
        assertThat(em.find(Author.class, 2).getSurname()).isEqualTo("Фамилия2");
        assertThat(em.find(Author.class, 1)).isNull();
        assertThatThrownBy(()-> em.flush()).isInstanceOf(PersistenceException.class);
    }

    @Test
    @DisplayName("должен искать и возвращать список авторов по фамилии")
    void searchBySurnameShouldFindAuthorByHisSurname() {
        List<Author> authorList = dao.searchBySurname(SECOND_AUTHOR_SURNAME);
        assertThat(authorList.size()).isEqualTo(1);
        assertThat(authorList.get(0).getSurname()).isEqualTo("Фамилия2");
    }
}