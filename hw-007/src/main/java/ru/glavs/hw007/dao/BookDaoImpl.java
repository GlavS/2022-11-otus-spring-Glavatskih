package ru.glavs.hw007.dao;

import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;
import ru.glavs.hw007.domain.Author;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Genre;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
                "select b from Book b order by b.id",
                Book.class
        );
        query.setHint(
                EntityGraphType.FETCH.getKey(),
                entityGraph);
        return query.getResultList();
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findByTitlePattern(String titlePattern) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.title like :pattern",
                Book.class
        );
        query.setParameter("pattern", "%" + titlePattern + "%");
        query.setHint(
                EntityGraphType.FETCH.getKey(),
                entityGraph);
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
    public void delete(Book bookToDelete) {
        em.remove(em.contains(bookToDelete) ? bookToDelete : em.merge(bookToDelete));
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(b) from Book b",
                Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAllWithCommentsOnly() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.comments is not empty",
                Book.class
        );
        query.setHint(EntityGraphType.FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

}
