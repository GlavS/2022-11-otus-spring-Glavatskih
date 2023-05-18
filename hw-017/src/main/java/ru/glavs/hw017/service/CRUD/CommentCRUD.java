package ru.glavs.hw017.service.CRUD;

import ru.glavs.hw017.domain.Comment;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

}
