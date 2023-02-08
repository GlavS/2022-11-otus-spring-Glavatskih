package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Book;

import java.util.List;
@Service
public class BookCRUDImpl implements BookCRUD {
    @Override
    public void save(Book book) {

    }

    @Override
    public List<Book> readAll() {
        return null;
    }

    @Override
    public List<Book> readAllWithCommentsOnly() {
        return null;
    }

    @Override
    public Book readBook(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
