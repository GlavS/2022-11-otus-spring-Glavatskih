package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Author;

import java.util.List;

public interface AuthorCRUD {
    Author create();
    List<Author> searchBySurname(String surname);
    void printList(List<Author> authorList);
    Author getById(int id);

}
