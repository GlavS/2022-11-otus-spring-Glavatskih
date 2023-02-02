package ru.glavs.hw007.service.CRUD;

import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.dto.BookWithCommentsDto;

public interface CommentCRUD {
    void delete(Comment comment);

    Comment save(Comment comment);

    Comment findById(long commentId);

    BookWithCommentsDto findCommentsByBook(long bookId);
}
