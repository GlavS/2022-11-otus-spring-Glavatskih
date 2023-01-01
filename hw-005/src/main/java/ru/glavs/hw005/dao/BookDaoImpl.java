package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{
    @Override
    public List<Book> getAll() {
        return null;
    }
}
