package ru.glavs.hw008.repository;

import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> updateComments(Book book);
}
