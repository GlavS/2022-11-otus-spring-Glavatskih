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


    public BookDaoImpl(NamedParameterJdbcOperations jdbc, AuthorDao authorDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT b.ID, AUTHOR_ID, GENRE_ID, TITLE, GENRE\n" +
                "FROM BOOKS b INNER JOIN GENRES g ON b.GENRE_ID = g.ID";
        return jdbc.query(sql, new BookRowMapper(authorDao));
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
        Author author =  book.getAuthor();
        Genre genre = book.getGenre();
        String sql = "UPDATE BOOKS SET TITLE = 'Old title'\n" +
                "WHERE ID = 31;";

    }

    @Override
    public void delete(Book book) {

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
