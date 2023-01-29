package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.CommentDao;
import ru.glavs.hw005.domain.Comment;

@Service
public class CommentCRUDImpl implements CommentCRUD {
    private final CommentDao dao;

    public CommentCRUDImpl(CommentDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void delete(Comment comment) {
       // Comment commentToDelete = dao.save(comment);
        dao.delete(comment);
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return dao.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment findById(long commentId) {
        return dao.getById(commentId);
    }
}
