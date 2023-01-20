package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    public BookDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public int insertNew(Author author, Genre genre, String title) {
        return 0;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int count() {
        return 0;
    }
}
