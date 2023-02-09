package ru.glavs.hw008.service.CRUD;

import org.bson.types.ObjectId;
import ru.glavs.hw008.domain.Comment;

import java.util.List;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

    List<Comment> findCommentsByBook(ObjectId bookId);
    List<Comment> findByCommentText(String partOfText);
}
