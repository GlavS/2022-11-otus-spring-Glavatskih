package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Comment;

public interface CommentDao {
    Comment save(Comment comment);

    void delete(Comment comment);

    Comment getById(long id);
}
