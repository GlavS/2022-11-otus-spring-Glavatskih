package ru.glavs.hw014.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw014.dao.AuthorDao;
import ru.glavs.hw014.dao.BookDao;
import ru.glavs.hw014.dao.GenreDao;
import ru.glavs.hw014.domain.Author;
import ru.glavs.hw014.domain.Book;
import ru.glavs.hw014.domain.Genre;

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
        List<Book> bookList = bookDao.findAll();
        for (Book b : bookList) {
            b.getComments().size();
        }
        return bookList;
    }

    @Transactional(readOnly = true)
    @Override
    public Book readBook(long id) {
        Book book = bookDao.getReferenceById(id);
        book.getComments().size();
        return book;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Book bookToDelete = bookDao.getReferenceById(id);
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
