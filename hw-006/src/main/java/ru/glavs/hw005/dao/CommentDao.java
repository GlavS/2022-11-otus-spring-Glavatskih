package ru.glavs.hw005.dao;

import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;

import java.util.List;

public interface CommentDao {
    Comment save(Comment comment);
    void delete(int id);
    List<Comment> getCommentsForBook(Book book);
}
