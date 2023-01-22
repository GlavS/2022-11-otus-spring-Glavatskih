package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Genre;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

    private static final long ALL_GENRES_NUMBER = 2;
    private static final int FIRST_GENRE_ID = 1;
    private static final Genre FIRST_GENRE = new Genre(1, "Жанр1");
    private static final Genre SECOND_GENRE = new Genre(2, "Жанр2");
    private static final Genre NEW_GENRE = new Genre(0, "Жанр3");
    private static final Genre THIRD_GENRE = new Genre(3, "Жанр3");
    private static final List<Genre> GENRE_LIST = List.of(FIRST_GENRE, SECOND_GENRE);
    private static final String SECOND_GENRE_GENRE = "Жанр2";
    @Autowired
    private GenreDaoImpl dao;

    @Autowired
    private EntityManager em;


    @Test
    @DisplayName("должен возвращать жанр по его id")
    void getByIdMethodShouldReturnGenreById() {
        Genre genre = dao.getById(FIRST_GENRE_ID);
        assertThat(genre).usingRecursiveComparison().isEqualTo(FIRST_GENRE);
    }

    @Test
    @DisplayName("должен возвращать список всех жанров")
    void getAllMethodShouldReturnCorrectGenresList() {
        List<Genre> genreList = dao.getAll();
        assertThat(genreList).usingRecursiveComparison().isEqualTo(GENRE_LIST);
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
        List<Genre> currentGenreList = new ArrayList<>();
        for (int i = 1; i <= ALL_GENRES_NUMBER + 1; i++) {
            currentGenreList.add(em.find(Genre.class, i));
        }
        assertThat(currentGenreList).hasSize(3).containsExactlyInAnyOrder(FIRST_GENRE, SECOND_GENRE, THIRD_GENRE);
    }

    @Test
    @DisplayName("должен удалять жанр с указанным id")
    void deleteShouldDeleteGenreByItsID() {
        dao.delete(FIRST_GENRE_ID);
        em.flush();
        List<Genre> afterDeleteList = List.of(em.find(Genre.class, 2));
        assertThat(afterDeleteList).usingRecursiveComparison().isEqualTo(List.of(SECOND_GENRE));
    }

    @Test
    @DisplayName("должен искать и возвращать жанр по его названию, или пустой жанр при его отсутствии")
    void searchByGenreShouldFindGenreByItsNameOrReturnEmpty() {
        Genre genre = dao.searchByGenre(SECOND_GENRE_GENRE);
        Genre genre2 = dao.searchByGenre("SECOND_GENRE_GENRE");
        assertThat(genre).usingRecursiveComparison().isEqualTo(SECOND_GENRE);
        assertThat(genre2.getId()).isEqualTo(0);
    }
}