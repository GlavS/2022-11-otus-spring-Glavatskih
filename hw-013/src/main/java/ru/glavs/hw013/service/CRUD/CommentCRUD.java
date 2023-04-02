package ru.glavs.hw013.service.CRUD;

import ru.glavs.hw013.domain.Comment;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

}
