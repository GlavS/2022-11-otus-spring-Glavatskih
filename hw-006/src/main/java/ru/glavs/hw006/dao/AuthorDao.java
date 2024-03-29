package ru.glavs.hw006.dao;

import ru.glavs.hw006.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(long id);

    List<Author> getAll();

    long count();

    Author save(Author author);

    void delete(Author author);

    List<Author> searchBySurname(String surname);
}
