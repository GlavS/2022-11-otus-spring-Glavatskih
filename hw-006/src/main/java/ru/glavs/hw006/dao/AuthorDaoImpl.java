package ru.glavs.hw006.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw006.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private final EntityManager em;

    public AuthorDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id)).orElseThrow();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a order by a.id",
                Author.class
        );
        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(a) from Author a",
                Long.class
        );
        return query.getSingleResult();
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public void delete(Author authorToDelete) {
        em.remove(em.contains(authorToDelete) ? authorToDelete : em.merge(authorToDelete));
    }

    @Override
    public List<Author> searchBySurname(String surname) {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a where a.surname = :surname",
                Author.class
        );
        query.setParameter("surname", surname);
        return query.getResultList();
    }
}
