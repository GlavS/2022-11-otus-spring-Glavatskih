package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private final EntityManager em;

    public CommentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment getById(int id) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.id = :id",
                Comment.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
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
    public void delete(int id) {
        Query query = em.createQuery(
                "delete from Comment c where c.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
