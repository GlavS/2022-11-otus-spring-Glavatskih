package ru.glavs.hw005.dao;

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
import java.util.List;
import java.util.Map;

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
        String sql = "SELECT b.ID, AUTHOR_ID, GENRE_ID, TITLE, GENRE\n" +
                "FROM BOOKS b INNER JOIN GENRES g ON b.GENRE_ID = g.ID";
        return jdbc.query(sql, new BookRowMapper(authorDao));
        //
    }

    @Override
    public Book getById(int id) {
        String sql = "SELECT b.ID, AUTHOR_ID, GENRE_ID, TITLE, GENRE\n" +
                "FROM BOOKS b INNER JOIN GENRES g ON b.GENRE_ID = g.ID\n" +
                "WHERE b.ID = :ID";
        Map<String, Integer> params = Map.of("ID", id);
        return jdbc.queryForObject(sql, params, new BookRowMapper(authorDao));
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
    public void delete(Book book) {
        Map<String, Integer> param = Map.of("ID", book.getId());
        jdbc.update("DELETE FROM BOOKS WHERE ID = :ID", param);
    }

    @Override
    public int count() {
        Integer result = jdbc
                .getJdbcOperations()
                .queryForObject("SELECT COUNT(*) FROM BOOKS", Integer.class);
        return isNull(result)? 0 : result;
    }





    private static class BookRowMapper implements RowMapper<Book> {
        private final AuthorDao authorDao;

        private BookRowMapper(AuthorDao authorDao) {
            this.authorDao = authorDao;
        }

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            int bookId = rs.getInt("ID");
            int authorId = rs.getInt("AUTHOR_ID");
            int genreId = rs.getInt("GENRE_ID");
            String bookTitle = rs.getString("TITLE");
            String genre = rs.getString("GENRE");
            Genre resultGenre = new Genre(genreId, genre);
            Author resultAuthor = authorDao.getById(authorId);
            return new Book(bookId, resultAuthor, resultGenre, bookTitle);
        }
    }
}
