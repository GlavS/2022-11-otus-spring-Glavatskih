package ru.glavs.hw007.service.CRUD;

import ru.glavs.hw007.domain.Book;

import java.util.List;

public interface BookCRUD {
    void save(Book book);

    List<Book> findAll();

    List<Book> readAllWithCommentsOnly();

    Book findById(long id);

    void deleteById(long id);
}
