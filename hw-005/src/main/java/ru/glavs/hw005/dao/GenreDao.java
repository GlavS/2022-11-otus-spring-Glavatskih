package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(int id);

    List<Genre> getAll();
    int count();
    int insertNew(Genre genre);
    void delete(Genre genre);
}
