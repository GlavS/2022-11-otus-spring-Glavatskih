package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(int id);

    List<Genre> getAll();

    int count();

    int insertNew(String genreName);

    void delete(int id);

    List<Genre> searchByGenre(String genreName);
}
