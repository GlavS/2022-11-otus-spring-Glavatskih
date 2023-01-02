package ru.glavs.hw005.dao;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Genre;

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
    void getById() {
        assertThat(genreDao.getById(EXPECTED_GENRE_ID)).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void getAll() {
        assertThat(genreDao.getAll().get(EXPECTED_GENRE_ID)).usingRecursiveComparison().isEqualTo(expectedGenre);
        assertThat(genreDao.getAll().size()).isEqualTo(GENRE_TESTDB_COUNT);
    }

    @Test
    void count() {
        assertThat(genreDao.count()).isEqualTo(GENRE_TESTDB_COUNT);
    }

    @Test
    void insertNew() {
        SoftAssertions insertBundle = new SoftAssertions();
        insertBundle.assertThat(genreDao.insertNew(newGenre)).isEqualTo(NEW_GENRE_ID);
        insertBundle.assertThat(genreDao.getById(NEW_GENRE_ID)).isInstanceOf(Genre.class);
        insertBundle.assertThat(genreDao.getById(NEW_GENRE_ID).getGenre()).isEqualTo("Жанр3");
        insertBundle.assertAll();
    }

    @Test
    void delete() {
        genreDao.delete(expectedGenre);
        SoftAssertions deleteBundle = new SoftAssertions();
        deleteBundle.assertThat(genreDao.getById(EXPECTED_GENRE_ID)).isNull();
        deleteBundle.assertThat(genreDao.count()).isEqualTo(GENRE_TESTDB_COUNT -1);
        deleteBundle.assertAll();
    }
}