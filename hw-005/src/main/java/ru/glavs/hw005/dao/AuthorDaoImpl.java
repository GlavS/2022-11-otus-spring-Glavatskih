package ru.glavs.hw005.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

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
        Integer result = jdbc
                .getJdbcOperations()
                .queryForObject("SELECT COUNT(*) FROM AUTHORS", Integer.class);
        return isNull(result)? 0 : result;
    }

    @Override
    public int insertNew(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("NAME", author.getName())
                .addValue("SURNAME", author.getSurname())
                .addValue("INITIALS", author.getInitials());
        jdbc.update("INSERT INTO AUTHORS(NAME, SURNAME, INITIALS) VALUES(:NAME, :SURNAME, :INITIALS)",
                params, keyHolder);
        Integer key = (Integer) keyHolder.getKey();
        return isNull(key)? 0 : key;
    }

    @Override
    public void delete(Author author) {
        Map<String, Integer> param = Map.of("ID", author.getId());
        jdbc.update("DELETE FROM AUTHORS WHERE ID = :ID", param);
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
