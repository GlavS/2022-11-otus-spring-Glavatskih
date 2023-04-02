package ru.glavs.hw012.service.CRUD;

import ru.glavs.hw012.domain.Comment;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

}
