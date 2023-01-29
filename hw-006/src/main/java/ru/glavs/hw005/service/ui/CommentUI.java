package ru.glavs.hw005.service.ui;

import ru.glavs.hw005.domain.Book;

public interface CommentUI {
    void createCommentFor(Book book);

    void deleteComment();
}
