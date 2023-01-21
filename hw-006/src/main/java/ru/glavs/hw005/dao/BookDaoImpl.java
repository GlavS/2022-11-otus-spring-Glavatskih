package ru.glavs.hw005.dao;

import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import javax.persistence.*;
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
        EntityGraph<?> entityGraph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b",
                Book.class
        );
        query.setHint(
                EntityGraphType.FETCH.getKey(),
                entityGraph);
        return query.getResultList();
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findByTitlePattern(String titlePattern) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.title like :pattern",
                Book.class
        );
        query.setParameter("pattern", "%" + titlePattern + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.author = :author",
                Book.class
        );
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.genre = :genre",
                Book.class
        );
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public void delete(int id) {
        Query query = em.createQuery(
                "delete from Book b where b.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(b) from Book b",
                Long.class);
        return query.getSingleResult();
    }

}
