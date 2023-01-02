package ru.glavs.hw005.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Author getById(int id) {
        Map<String, Integer> param = Map.of("id", id);
        return jdbc.queryForObject("SELECT id, name, surname, initials FROM AUTHORS WHERE id = :id", param, new AuthorRowMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("SELECT id, name, surname, initials FROM AUTHORS", new AuthorRowMapper());
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int insertNew(Author author) {
        return 0;
    }

    @Override
    public void delete(Author author) {

    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("initials"));
        }
    }
}
