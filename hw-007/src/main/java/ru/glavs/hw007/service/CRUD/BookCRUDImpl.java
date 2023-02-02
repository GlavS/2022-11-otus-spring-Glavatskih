package ru.glavs.hw007.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw007.dao.AuthorDao;
import ru.glavs.hw007.dao.BookDao;
import ru.glavs.hw007.dao.GenreDao;
import ru.glavs.hw007.domain.Author;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Genre;

import java.util.List;

@Service
public class BookCRUDImpl implements BookCRUD {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookCRUDImpl(BookDao bookDao,
                        AuthorDao authorDao,
                        GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Transactional
    @Override
    public void save(Book book) {
        Author author = authorDao.save(book.getAuthor());
        book.setAuthor(author);
        Genre genre = genreDao.save(book.getGenre());
        book.setGenre(genre);
        bookDao.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> readAll() {
        List<Book> bookList = bookDao.getAll();
        for (Book b : bookList) {
            b.getComments().size();
        }
        return bookList;
    }

    @Transactional(readOnly = true)
    @Override
    public Book readBook(long id) {
        Book book = bookDao.getById(id);
        book.getComments().size();
        return book;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Book bookToDelete = bookDao.getById(id);
        bookDao.delete(bookToDelete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> readAllWithCommentsOnly() {
        List<Book> bookList = bookDao.getAllWithCommentsOnly();
        for (Book b : bookList) {
            b.getComments().size();
        }
        return bookList;
    }
}
