package ru.glavs.hw006.service.CRUD;

import ru.glavs.hw006.domain.Book;
import ru.glavs.hw006.domain.Comment;
import ru.glavs.hw006.dto.BookWithCommentsDto;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

    BookWithCommentsDto findCommentsByBook(long bookId);
}
