package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return null;
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
    public int insertNew(String name, String surname, String initials) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Author> searchBySurname(String surname) {
        return null;
    }
}
