package ru.glavs.hw006.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw006.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    private final EntityManager em;

    public GenreDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id)).orElseThrow();
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g order by g.id",
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
    public void delete(Genre genreToDelete) {
        em.remove(em.contains(genreToDelete) ? genreToDelete : em.merge(genreToDelete));
    }

    @Override
    public Genre searchByGenre(String genreName) {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g where g.genre = :genreName",
                Genre.class
        );
        query.setParameter("genreName", genreName);
        Genre result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            return new Genre();
        }
        return result;
    }
}
