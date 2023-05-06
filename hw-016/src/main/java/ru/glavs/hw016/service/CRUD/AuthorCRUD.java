package ru.glavs.hw016.service.CRUD;

import ru.glavs.hw016.domain.Author;

import java.util.List;

public interface AuthorCRUD {
    List<Author> searchBySurname(String surname);

    List<Author> findAll();

    Author save(Author author);

    Author findById(long id);
}
