package ru.glavs.hw014.service.CRUD;

import ru.glavs.hw014.domain.Genre;

import java.util.List;

public interface GenreCRUD {
    Genre searchByGenre(String genreName);

    List<Genre> findAll();

    Genre save(Genre genre);

    Genre findById(long id);
}
