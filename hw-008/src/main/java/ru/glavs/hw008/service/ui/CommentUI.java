package ru.glavs.hw008.service.ui;

import ru.glavs.hw008.domain.Book;

public interface CommentUI {

    void createCommentFor(Book book, String commentText, String nickName);

    void deleteComment();

    String prepareCommentText();
}
