package ru.glavs.hw006.service.CRUD;

import ru.glavs.hw006.domain.Book;

import java.util.List;

public interface BookCRUD {
    void save(Book book);

    List<Book> readAll();

    List<Book> readAllWithCommentsOnly();

    Book readBook(long id);

    void deleteById(long id);
}
