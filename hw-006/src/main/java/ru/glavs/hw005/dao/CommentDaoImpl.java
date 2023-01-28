package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private final EntityManager em;

    public CommentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment getById(int id) {
        return Optional.ofNullable(em.find(Comment.class, id)).orElseThrow();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public void delete(Comment commentToDelete) {
        em.remove(commentToDelete);
    }
}
