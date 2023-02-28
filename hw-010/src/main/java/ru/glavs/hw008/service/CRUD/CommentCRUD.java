package ru.glavs.hw008.service.CRUD;

import org.bson.types.ObjectId;
import ru.glavs.hw008.domain.Comment;

import java.util.List;

public interface CommentCRUD {
    void deleteAll(List<Comment> commentList);

    void delete(Comment comment);

    Comment save(Comment comment);

    List<Comment> findCommentsByBook(String bookId);

    List<Comment> findByCommentText(String partOfText);
    Comment findById(String id);
}
