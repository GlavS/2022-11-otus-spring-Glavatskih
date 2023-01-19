package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Genre;

import java.util.List;
@Repository
public class GenreDaoImpl implements GenreDao {
    @Override
    public Genre getById(int id) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Genre save(Genre genre) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Genre searchByGenre(String genreName) {
        return null;
    }
}
