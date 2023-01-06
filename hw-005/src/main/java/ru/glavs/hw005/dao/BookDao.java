package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getById(int id);
    int insertNew(Book book);
    void update(Book book);
    void delete(int id);
    int count();
}
