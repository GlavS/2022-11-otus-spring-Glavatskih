package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private EntityManager em;

    public AuthorDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author getById(int id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a",
                Author.class
        );
        return query.getResultList();
    }

    @Override
    public Long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(*) from Author a",
                Long.class
        );
        return query.getSingleResult();
    }

    @Override
    public Author save(Author author) {
        if(author.getId() == 0){
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public void delete(int id) {
        Author forDelete = getById(id);
        em.remove(forDelete);
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
