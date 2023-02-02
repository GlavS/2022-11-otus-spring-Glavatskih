package ru.glavs.hw007.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw007.domain.Comment;

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
    public Comment getById(long id) {
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
        em.remove(em.contains(commentToDelete) ? commentToDelete : em.merge(commentToDelete));
    }
}
