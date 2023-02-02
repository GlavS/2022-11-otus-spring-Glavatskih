package ru.glavs.hw007.dao;

import ru.glavs.hw007.domain.Comment;

public interface CommentDao {
    Comment save(Comment comment);

    void delete(Comment comment);

    Comment getById(long id);
}
