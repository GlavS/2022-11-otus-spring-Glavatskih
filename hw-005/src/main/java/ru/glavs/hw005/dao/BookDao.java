package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
}
