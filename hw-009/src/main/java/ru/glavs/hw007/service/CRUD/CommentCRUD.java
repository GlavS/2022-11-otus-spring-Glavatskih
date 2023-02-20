package ru.glavs.hw007.service.CRUD;

import ru.glavs.hw007.domain.Comment;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

}
