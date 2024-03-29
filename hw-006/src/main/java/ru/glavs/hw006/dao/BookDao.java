package ru.glavs.hw006.dao;

import ru.glavs.hw006.domain.Author;
import ru.glavs.hw006.domain.Book;
import ru.glavs.hw006.domain.Genre;

import java.util.List;

public interface BookDao {
    List<Book> getAll();

    Book getById(long id);

    List<Book> findByTitlePattern(String titlePattern);

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(Genre genre);

    Book save(Book book);

    void delete(Book book);

    long count();

    List<Book> getAllWithCommentsOnly();
}
