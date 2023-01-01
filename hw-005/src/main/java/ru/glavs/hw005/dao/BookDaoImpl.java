package ru.glavs.hw005.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        return jdbc.getJdbcOperations().query(sql, new BookRowMapper(authorDao));
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
