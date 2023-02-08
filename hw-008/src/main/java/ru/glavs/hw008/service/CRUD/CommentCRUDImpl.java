package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Comment;

import java.util.List;
@Service
public class CommentCRUDImpl implements CommentCRUD {
    @Override
    public void delete(Comment comment) {

    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Comment findById(long commentId) {
        return null;
    }

    @Override
    public List<Comment> findCommentsByBook(long bookId) {
        return null;
    }
}
