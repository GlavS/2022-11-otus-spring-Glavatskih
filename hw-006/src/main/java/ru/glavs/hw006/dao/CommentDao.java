package ru.glavs.hw006.dao;

import ru.glavs.hw006.domain.Comment;

public interface CommentDao {
    Comment save(Comment comment);

    void delete(Comment comment);

    Comment getById(long id);
}
