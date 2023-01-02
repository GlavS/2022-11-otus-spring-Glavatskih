package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Genre;

import java.util.List;
@Repository
public class GenreDaoImpl implements GenreDao{
    @Override
    public Genre getById(int id) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int insertNew(Genre genre) {
        return 0;
    }

    @Override
    public void delete(Genre genre) {

    }
}
