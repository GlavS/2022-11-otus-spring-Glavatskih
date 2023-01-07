package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getById(int id);
    int insertNew(Author author, Genre genre, String title);
    void update(Book book);
    void delete(int id);
    int count();
}
