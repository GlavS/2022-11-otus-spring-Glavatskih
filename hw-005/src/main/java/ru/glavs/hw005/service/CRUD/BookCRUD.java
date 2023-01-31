package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Book;

public interface BookCRUD {
    Book create();
    void readAll();
    void readBook(int id);
    void update();
    void delete(int id);
}
