package ru.glavs.hw006.service.CRUD;

import ru.glavs.hw006.domain.Author;

import java.util.List;

public interface AuthorCRUD {
    List<Author> searchBySurname(String surname);

    List<Author> findAll();

    Author save(Author author);

    Author findById(long id);
}
