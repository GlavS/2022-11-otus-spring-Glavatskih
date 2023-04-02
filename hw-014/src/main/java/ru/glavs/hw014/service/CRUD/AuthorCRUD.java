package ru.glavs.hw014.service.CRUD;

import ru.glavs.hw014.domain.Author;

import java.util.List;

public interface AuthorCRUD {
    List<Author> searchBySurname(String surname);

    List<Author> findAll();

    Author save(Author author);

    Author findById(long id);
}
