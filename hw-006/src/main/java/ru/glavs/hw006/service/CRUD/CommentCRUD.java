package ru.glavs.hw006.service.CRUD;

import ru.glavs.hw006.domain.Comment;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);
}
