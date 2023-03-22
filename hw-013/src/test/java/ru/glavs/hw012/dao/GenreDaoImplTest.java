package ru.glavs.hw012.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.glavs.hw012.domain.Genre;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@DisplayName("Класс GenreDao должен")
class GenreDaoImplTest {

    private static final long ALL_GENRES_NUMBER = 2;
    private static final long FIRST_GENRE_ID = 1;

    private static final Genre NEW_GENRE = new Genre("Жанр3");
    private static final String SECOND_GENRE_GENRE = "Жанр2";
    @Autowired
    private GenreDao dao;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("должен возвращать жанр по его id")
    void getByIdMethodShouldReturnGenreById() {
        Genre genre = dao.getReferenceById(FIRST_GENRE_ID);
        assertThat(genre.getName()).isEqualTo("Жанр1");
    }

    @Test
    @DisplayName("должен возвращать список всех жанров")
    void getAllMethodShouldReturnCorrectGenresList() {
        List<Genre> genreList = dao.findAll();
        assertThat(genreList.size()).isEqualTo(ALL_GENRES_NUMBER);
        assertThat(genreList.get(0).getName()).isEqualTo("Жанр1");
    }

    @Test
    @DisplayName("должен посчитать количество всех жанров")
    void countMethodShouldReturnGenresQuantity() {
        long count = dao.count();
        assertThat(count).isEqualTo(ALL_GENRES_NUMBER);
    }

    @Test
    @DisplayName("должен добавлять в БД новый жанр")
    void saveMethodShouldAddNewGenreToDatabase() {
        dao.save(NEW_GENRE);
        em.flush();
        List<Genre> currentGenreList = new ArrayList<>();
        for (long i = 1; i <= ALL_GENRES_NUMBER + 1; i++) {
            currentGenreList.add(em.find(Genre.class, i));
        }
        assertThat(currentGenreList).hasSize(3);
        assertThat(currentGenreList.get(2).getName()).isEqualTo("Жанр3");

    }

    @Test
    @DisplayName("должен удалять жанр")
    void deleteShouldDeleteGenre() {
        Genre genreToDelete = em.find(Genre.class, 1L);
        assertThat(em.find(Genre.class, FIRST_GENRE_ID)).isNotNull();
        dao.delete(genreToDelete);
        assertThat(em.find(Genre.class, FIRST_GENRE_ID)).isNull();
        assertThatThrownBy(() -> em.flush()).isInstanceOf(PersistenceException.class);
    }

    @Test
    @DisplayName("должен искать и возвращать жанр по его названию, или пустой жанр при его отсутствии")
    void searchByGenreShouldFindGenreByItsNameOrReturnEmpty() {
        Genre genre = dao.findByName(SECOND_GENRE_GENRE);
        Genre genre2 = dao.findByName("SECOND_GENRE_GENRE");
        assertThat(genre).isNotNull();
        assertThat(genre2).isNull();
    }
}