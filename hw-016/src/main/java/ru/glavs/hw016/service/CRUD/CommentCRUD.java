package ru.glavs.hw016.service.CRUD;

import ru.glavs.hw016.domain.Comment;

import java.util.List;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

    List<Comment> findCommentsByBook(long bookId);
}
