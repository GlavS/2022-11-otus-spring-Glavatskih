package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    private final EntityManager em;

    public GenreDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre getById(int id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g",
                Genre.class
        );
        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(g) from Genre g",
                Long.class
        );
        return query.getSingleResult();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public void delete(int id) {
        Query query = em.createQuery(
                "delete from Genre g where g.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Genre searchByGenre(String genreName) {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g where g.genre = :genreName",
                Genre.class
        );
        query.setParameter("genreName", genreName);
        return query.getSingleResult();
    }
}
