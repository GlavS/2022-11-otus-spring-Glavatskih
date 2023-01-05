package ru.glavs.hw005.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Repository
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Genre getById(int id) {
        Map<String, Integer> param = Map.of("ID", id);
        return jdbc.queryForObject("SELECT ID, GENRE FROM GENRES WHERE ID = :ID", param, new GenreRowMapper());
    }

    @Override
    public List<Genre> getAll() {
        String sql = "SELECT ID, GENRE FROM GENRES";
        return jdbc.query(sql, new GenreRowMapper());
    }

    @Override
    public int count() {
        Integer result = jdbc.getJdbcOperations()
                .queryForObject("SELECT COUNT(*) FROM GENRES", Integer.class);
        return isNull(result) ? 0 : result;
    }

    @Override
    public int insertNew(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("GENRE", genre.getGenre());
        String sql = "INSERT INTO GENRES(GENRE) VALUES(:GENRE)";
        jdbc.update(sql, param, keyHolder);
        Integer generatedKey = (Integer) keyHolder.getKey();
        return isNull(generatedKey) ? 0 : generatedKey;
    }

    @Override
    public void delete(Genre genre) {
        String sql = "DELETE FROM GENRES WHERE ID = :ID";
        Map<String, Integer> param = Map.of("ID", genre.getId());
        jdbc.update(sql, param);
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getInt("ID"), rs.getString("GENRE"));
        }
    }
}
