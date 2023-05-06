package ru.glavs.hw016.service.ui;

import ru.glavs.hw016.domain.Book;

public interface CommentUI {

    void createCommentFor(Book book, String commentText, String nickName);

    void deleteComment();

    String prepareCommentText();
}
