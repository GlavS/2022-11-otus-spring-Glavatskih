package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(GenreDaoImpl.class)
class GenreDaoTest {

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
        List<Genre> currentGenreList = dao.getAll();
        assertThat(currentGenreList).hasSize(3).containsExactlyInAnyOrder(FIRST_GENRE, SECOND_GENRE, THIRD_GENRE);
    }

    @Test
    @DisplayName("должен удалять жанр с указанным id")
    void deleteShouldDeleteGenreByItsID() {
        dao.delete(FIRST_GENRE_ID);
        List<Genre> afterDeleteList = dao.getAll();
        assertThat(afterDeleteList).usingRecursiveComparison().isEqualTo(List.of(SECOND_GENRE));
    }

    @Test
    @DisplayName("должен искать и возвращать жанр по его названию")
    void searchByGenreShouldFindGenreByItsName() {
        Genre genreList = dao.searchByGenre(SECOND_GENRE_GENRE);
        assertThat(genreList).usingRecursiveComparison().isEqualTo(List.of(SECOND_GENRE));
    }
}