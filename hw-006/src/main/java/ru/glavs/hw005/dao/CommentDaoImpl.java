package ru.glavs.hw005.dao;

import org.springframework.stereotype.Repository;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private final EntityManager em;

    public CommentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment getById(int id) {
        return null;
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Comment> getCommentsForBook(Book book) {
        return null;
    }
}
