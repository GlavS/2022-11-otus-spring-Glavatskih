package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.BookDao;
import ru.glavs.hw005.domain.Book;

import java.util.List;

@Service
public class BookCRUDImpl implements BookCRUD {
    private final BookDao dao;

    public BookCRUDImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Book create() {

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> readAll() {
        return dao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book readBook(int id) {
        return dao.getById(id);
    }

    @Transactional
    @Override
    public void update() {

    }

    @Transactional
    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
