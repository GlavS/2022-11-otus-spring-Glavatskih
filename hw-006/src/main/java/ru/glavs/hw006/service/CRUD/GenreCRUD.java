package ru.glavs.hw006.service.CRUD;

import ru.glavs.hw006.domain.Genre;

import java.util.List;

public interface GenreCRUD {
    Genre searchByGenre(String genreName);

    List<Genre> findAll();

    Genre save(Genre genre);

    Genre findById(long id);
}
