package ru.glavs.hw013.service.CRUD;

import ru.glavs.hw013.domain.Author;

import java.util.List;

public interface AuthorCRUD {

    List<Author> findAll();

    Author save(Author author);

    Author findById(long id);
}
