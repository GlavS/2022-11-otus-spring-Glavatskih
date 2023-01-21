package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Book;

import java.util.List;

public interface BookCRUD {
    Book create();
    List<Book> readAll();
    Book readBook(int id);
    void update();
    void delete(int id);
}
