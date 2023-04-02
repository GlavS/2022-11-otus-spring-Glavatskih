package ru.glavs.hw012.service.CRUD;

import ru.glavs.hw012.domain.Genre;

import java.util.List;

public interface GenreCRUD {
    List<Genre> findAll();

    Genre save(Genre genre);

    Genre findById(long id);
}
