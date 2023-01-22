package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.BookDao;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.service.ui.BookUserInterface;

import java.util.List;

@Service
public class BookCRUDImpl implements BookCRUD {
    private final BookDao dao;
    private final BookUserInterface bookUi;

    public BookCRUDImpl(BookDao dao, BookUserInterface bookUi) {
        this.dao = dao;
        this.bookUi = bookUi;
    }

    @Transactional
    @Override
    public void create() {

        dao.save(bookUi.BIGMETHOD());//TODO:!!

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
