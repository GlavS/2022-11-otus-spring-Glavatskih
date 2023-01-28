package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Author;

import java.util.List;

public interface AuthorCRUD {
    List<Author> searchBySurname(String surname);

    List<Author> findAll();

    Author save(Author author);

    Author findById(long id);
}
