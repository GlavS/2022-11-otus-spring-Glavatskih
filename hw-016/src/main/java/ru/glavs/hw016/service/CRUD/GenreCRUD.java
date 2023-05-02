package ru.glavs.hw016.service.CRUD;

import ru.glavs.hw016.domain.Genre;

import java.util.List;

public interface GenreCRUD {
    Genre searchByGenre(String genreName);

    List<Genre> findAll();

    Genre save(Genre genre);

    Genre findById(long id);
}
