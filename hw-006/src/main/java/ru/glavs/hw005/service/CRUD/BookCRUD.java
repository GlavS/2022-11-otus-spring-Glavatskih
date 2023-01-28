package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Book;

import java.util.List;

public interface BookCRUD {
    void save(Book book);

    List<Book> readAll();

    List<Book> readAllWithCommentsOnly();

    Book readBook(int id);

    void delete(int id);
}
