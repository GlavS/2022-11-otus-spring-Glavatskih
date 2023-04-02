package ru.glavs.hw014.service.ui;

import ru.glavs.hw014.domain.Book;

public interface CommentUI {

    void createCommentFor(Book book, String commentText, String nickName);

    void deleteComment();

    String prepareCommentText();
}
