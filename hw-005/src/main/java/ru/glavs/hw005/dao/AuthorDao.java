package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(int id);

    List<Author> getAll();
    int count();
    int insertNew(Author author);
    void delete(Author author);
}
