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

    @Transactional
    @Override
    public void save(Book book) {
        dao.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> readAll() {
        List<Book> bookList = dao.getAll();
        for (Book b : bookList) {
            b.getComments().size();
        }
        return bookList;
    }

    @Transactional(readOnly = true)
    @Override
    public Book readBook(long id) {
        Book book = dao.getById(id);
        book.getComments().size();
        return book;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Book bookToDelete = dao.getById(id);
        dao.delete(bookToDelete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> readAllWithCommentsOnly() {
        List<Book> bookList = dao.getAllWithCommentsOnly();
        for (Book b : bookList) {
            b.getComments().size();
        }
        return bookList;
    }
}
