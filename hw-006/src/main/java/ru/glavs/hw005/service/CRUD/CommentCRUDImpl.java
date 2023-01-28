package ru.glavs.hw005.service.CRUD;

import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.CommentDao;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;

public class CommentCRUDImpl implements CommentCRUD {
    private final CommentDao dao;

    public CommentCRUDImpl(CommentDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void delete(Comment comment) {

    }

    @Override
    public void addCommentFor(Book book) {

    }
}
