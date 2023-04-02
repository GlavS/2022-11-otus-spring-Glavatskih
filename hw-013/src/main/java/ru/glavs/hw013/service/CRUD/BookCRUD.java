package ru.glavs.hw013.service.CRUD;

import ru.glavs.hw013.domain.Book;

import java.util.List;

public interface BookCRUD {
    void save(Book book);

    List<Book> findAll();

    Book findById(long id);

    void deleteById(long id);
}
