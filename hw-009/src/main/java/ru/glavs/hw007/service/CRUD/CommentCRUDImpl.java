package ru.glavs.hw007.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw007.dao.CommentDao;
import ru.glavs.hw007.domain.Comment;

@Service
public class CommentCRUDImpl implements CommentCRUD {
    private final CommentDao commentDao;

    public CommentCRUDImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    @Transactional
    public Comment findById(long commentId) {
        Comment comment = commentDao.getReferenceById(commentId);
        comment.getCommentedBook();
        return comment;
    }
}
