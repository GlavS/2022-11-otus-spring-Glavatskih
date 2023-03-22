package ru.glavs.hw012.service.CRUD;

import ru.glavs.hw012.domain.Author;

import java.util.List;

public interface AuthorCRUD {

    List<Author> findAll();

    Author save(Author author);

    Author findById(long id);
}
