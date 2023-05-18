package ru.glavs.hw017.service.CRUD;

import ru.glavs.hw017.domain.Genre;

import java.util.List;

public interface GenreCRUD {
    List<Genre> findAll();

    Genre save(Genre genre);

    Genre findById(long id);
}
