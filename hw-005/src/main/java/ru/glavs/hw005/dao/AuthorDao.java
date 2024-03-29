package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(int id);

    List<Author> getAll();
    int count();
    int insertNew(String name, String surname, String initials);
    void delete(int id);
    List<Author> searchBySurname(String surname);
}
