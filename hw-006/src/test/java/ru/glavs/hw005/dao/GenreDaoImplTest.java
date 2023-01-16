package ru.glavs.hw005.dao;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(GenreDaoImpl.class)
@DisplayName("В классе GenreDaoImpl")
class GenreDaoImplTest {
    private static final int GENRE_TESTDB_COUNT = 2;
    private static final int EXPECTED_GENRE_ID = 2;
    private static final int NEW_GENRE_ID = 3;
    private Genre expectedGenre;
    private Genre newGenre;
    @Autowired
    private GenreDaoImpl genreDao;

    @BeforeEach
    void init() {
        this.expectedGenre = new Genre(EXPECTED_GENRE_ID, "Жанр2");
        this.newGenre = new Genre(NEW_GENRE_ID, "Жанр3");
    }

    @Test
    @DisplayName("метод getById должен возвращать объект Genre по его id")
    void getByIdShouldReturnCorrectGenreById() {
        assertThat(genreDao.getById(EXPECTED_GENRE_ID)).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("метод getAll должен возвращать список всех жанров")
    void getAllShouldReturnExpectedGenreList() {
        SoftAssertions getAllBundle = new SoftAssertions();
        getAllBundle.assertThat(genreDao.getAll()).isInstanceOf(List.class);
        getAllBundle.assertThat(genreDao.getAll().get(0)).isInstanceOf(Genre.class);
        getAllBundle.assertThat(genreDao.getAll().size()).isEqualTo(GENRE_TESTDB_COUNT);
        getAllBundle.assertAll();
    }

    @Test
    @DisplayName("метод count должен вернуть количество жанров, сохраненных в БД")
    void countMethodShouldReturnCorrectNumberOfStoredGenres() {
        assertThat(genreDao.count()).isEqualTo(GENRE_TESTDB_COUNT);
    }

    @Test
    @DisplayName("метод insertNew должен сохранять в БД новый жанр")
    void insertNewMethodShouldSaveNewGenreObjectToDatabase() {
        int key = genreDao.insertNew("Жанр3");
        SoftAssertions insertBundle = new SoftAssertions();
        insertBundle.assertThat(key).isEqualTo(NEW_GENRE_ID);
        insertBundle.assertThat(genreDao.getById(key)).isInstanceOf(Genre.class);
        insertBundle.assertThat(genreDao.getById(key).getGenre()).isEqualTo("Жанр3");
        insertBundle.assertThat(genreDao.getById(key)).usingRecursiveComparison().isEqualTo(newGenre);
        insertBundle.assertAll();
    }

    @Test
    @DisplayName("метод delete должен удалять выбранный жанр из БД")
    void deleteMethodShouldCorrectlyDeleteGenreFromDatabase() {
        genreDao.delete(expectedGenre.getId());
        SoftAssertions deleteBundle = new SoftAssertions();
        deleteBundle.assertThatThrownBy(() -> genreDao.getById(EXPECTED_GENRE_ID)).isInstanceOf(EmptyResultDataAccessException.class);
        deleteBundle.assertThat(genreDao.count()).isEqualTo(GENRE_TESTDB_COUNT - 1);
        deleteBundle.assertAll();
    }

    @Test
    @DisplayName("метод searchByGenre должен вернуть список совпадений")
    void searchByGenreMethodShouldReturnExpectedGenreList() {
        List<Genre> genreList = genreDao.searchByGenre("Жа");
        SoftAssertions searchBundle = new SoftAssertions();
        searchBundle.assertThat(genreList.size()).isEqualTo(2);
        searchBundle.assertThat(genreList.get(0).getGenre()).isEqualTo("Жанр1");
        searchBundle.assertAll();
    }
}