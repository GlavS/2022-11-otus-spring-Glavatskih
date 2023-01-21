package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Comment;

public interface CommentDao {
    Comment save(Comment comment);

    void delete(int id);

    Comment getById(int id);
}
