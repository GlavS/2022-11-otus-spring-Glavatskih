package ru.glavs.hw008.service.CRUD;

import ru.glavs.hw008.domain.Author;

import java.util.List;

public interface AuthorCRUD {
    List<Author> searchBySurname(String surname);

    List<Author> findAll();

    List<Author> saveAll(List<Author> authorList);

    Author findById(long id);
}
