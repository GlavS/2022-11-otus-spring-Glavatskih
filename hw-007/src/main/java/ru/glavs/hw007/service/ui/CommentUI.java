package ru.glavs.hw007.service.ui;

import ru.glavs.hw007.domain.Book;

public interface CommentUI {

    void createCommentFor(Book book, String commentText, String nickName);

    void deleteComment();

    String prepareCommentText();
}
