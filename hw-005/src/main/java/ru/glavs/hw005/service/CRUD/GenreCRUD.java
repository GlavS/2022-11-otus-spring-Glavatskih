package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Genre;

import java.util.List;

public interface GenreCRUD {
    Genre create();

    List<Genre> searchByGenre(String surname);

    void printList(List<Genre> authorList);
    void printAll();

    Genre getById(int id);
}
