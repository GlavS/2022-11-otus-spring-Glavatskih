package ru.glavs.hw006.service.ui;

import ru.glavs.hw006.domain.Book;

public interface CommentUI {

    void createCommentFor(Book book, String commentText, String nickName);

    void deleteComment();

    String prepareCommentText();
}
