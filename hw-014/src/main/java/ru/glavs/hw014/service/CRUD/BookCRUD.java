package ru.glavs.hw014.service.CRUD;

import ru.glavs.hw014.domain.Book;

import java.util.List;

public interface BookCRUD {
    void save(Book book);

    List<Book> readAll();

    List<Book> readAllWithCommentsOnly();

    Book readBook(long id);

    void deleteById(long id);
}
