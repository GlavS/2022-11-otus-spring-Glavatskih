package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.CommentDao;
import ru.glavs.hw005.domain.Book;
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
        dao.delete(comment);
    }

    @Override
    @Transactional
    public void addCommentFor(Book book, Comment comment) {
        comment.setBook(book);
        dao.save(comment);
    }
}
