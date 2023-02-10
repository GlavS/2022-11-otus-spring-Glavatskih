package ru.glavs.hw008.service.CRUD;

import ru.glavs.hw008.domain.Genre;

import java.util.List;

public interface GenreCRUD {
    List<Genre> searchByGenre(String genreNamePart);

    List<Genre> findAll();

    List<Genre> saveAll(List<Genre> genreList);

    Genre findById(long id);
}
