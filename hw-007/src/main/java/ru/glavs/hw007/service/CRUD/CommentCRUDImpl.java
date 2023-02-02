package ru.glavs.hw007.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw007.dao.BookDao;
import ru.glavs.hw007.dao.CommentDao;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.dto.BookWithCommentsDto;

@Service
public class CommentCRUDImpl implements CommentCRUD {
    private final CommentDao commentDao;
    private final BookDao bookDao;

    public CommentCRUDImpl(CommentDao commentDao, BookDao bookDao) {
        this.commentDao = commentDao;
        this.bookDao = bookDao;
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
    public Comment findById(long commentId) {
        return commentDao.getById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public BookWithCommentsDto findCommentsByBook(long bookId){
        Book bookFromContext = bookDao.getById(bookId);
        return BookWithCommentsDto.fromDomainObject(bookFromContext);
    }
}
