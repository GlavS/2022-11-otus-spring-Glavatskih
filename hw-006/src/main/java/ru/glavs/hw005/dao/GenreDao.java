package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(long id);

    List<Genre> getAll();

    long count();

    Genre save(Genre genre);

    void delete(Genre genre);

    Genre searchByGenre(String genreName);
}
