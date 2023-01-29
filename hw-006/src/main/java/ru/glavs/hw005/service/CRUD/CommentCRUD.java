package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;

public interface CommentCRUD {
    void delete(Comment comment);
    Comment save(Comment comment);

    Comment findById(long commentId);
}
