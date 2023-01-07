package ru.glavs.hw005.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;


    public BookDaoImpl(NamedParameterJdbcOperations jdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public List<Book> getAll() {
        List<Author> authorList = authorDao.getAll();
        List<Genre> genreList = genreDao.getAll();
        String sql = "SELECT ID, AUTHOR_ID, GENRE_ID, TITLE FROM BOOKS";
        List<Book> bookList = jdbc.query(sql, new BookRowMapper());
        return mapBooks(bookList, authorList, genreList);
    }

    private List<Book> mapBooks(List<Book> tempBookList, List<Author> authorList, List<Genre> genreList) {
        List<Book> resultList = new ArrayList<>();
        for (Book tempBook : tempBookList) {
            resultList.add(new Book(
                    tempBook.getId(),
                    authorList.stream()
                            .filter(author -> author.getId() == tempBook.getAuthor().getId())
                            .findFirst()
                            .orElseThrow(() -> new EmptyResultDataAccessException("No book author found", 1)),
                    genreList.stream()
                            .filter(genre -> genre.getId() == tempBook.getGenre().getId())
                            .findFirst()
                            .orElseThrow(() -> new EmptyResultDataAccessException("No book genre found", 1)),
                    tempBook.getTitle()
            ));
        }
        return resultList;
    }

    @Override
    public Book getById(int id) {
        String sql = "SELECT ID, AUTHOR_ID, GENRE_ID, TITLE FROM BOOKS WHERE ID = :ID";
        Map<String, Integer> param = Map.of("ID", id);
        try {
            Book tempBook = jdbc.queryForObject(sql, param, new BookRowMapper());
            assert tempBook != null;
            Optional<Author> optionalAuthor = Optional.of(authorDao.getById(tempBook.getAuthor().getId()));
            Optional<Genre> optionalGenre = Optional.of(genreDao.getById(tempBook.getGenre().getId()));
            return new Book(tempBook.getId(),
                    optionalAuthor.orElseThrow(() -> new EmptyResultDataAccessException("No book author found", 1)),
                    optionalGenre.orElseThrow(() -> new EmptyResultDataAccessException("No book genre found", 1)),
                    tempBook.getTitle());
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not get book by id: " + e.getMessage(), e);
        }
    }

    @Override
    public int insertNew(Book book) {
        String sql = "INSERT INTO BOOKS (AUTHOR_ID, GENRE_ID, TITLE) VALUES ( :AUTHOR_ID, :GENRE_ID, :TITLE )";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("AUTHOR_ID", book.getAuthor().getId())
                .addValue("GENRE_ID", book.getGenre().getId())
                .addValue("TITLE", book.getTitle());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, generatedKeyHolder);
        return isNull(generatedKeyHolder.getKey()) ? 0 : (int) generatedKeyHolder.getKey();
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = Map.of(
                "ID", book.getId(),
                "AUTHOR_ID", book.getAuthor().getId(),
                "GENRE_ID", book.getGenre().getId(),
                "TITLE", book.getTitle()
        );

        String sql = "UPDATE BOOKS " +
                "SET AUTHOR_ID = :AUTHOR_ID, " +
                "GENRE_ID = :GENRE_ID, " +
                "TITLE = :TITLE " +
                "WHERE ID = :ID";
        jdbc.update(sql, params);
    }

    @Override
    public void delete(int id) {
        Map<String, Integer> param = Map.of("ID", id);
        jdbc.update("DELETE FROM BOOKS WHERE ID = :ID", param);
    }

    @Override
    public int count() {
        Integer result = jdbc
                .getJdbcOperations()
                .queryForObject("SELECT COUNT(*) FROM BOOKS", Integer.class);
        return isNull(result) ? 0 : result;
    }


    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            int bookId = rs.getInt("ID");
            int authorId = rs.getInt("AUTHOR_ID");
            int genreId = rs.getInt("GENRE_ID");
            String bookTitle = rs.getString("TITLE");
            return new Book(bookId,
                    new Author(authorId, "", "", ""),
                    new Genre(genreId, ""),
                    bookTitle);
        }
    }
}
