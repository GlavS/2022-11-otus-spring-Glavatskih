package ru.glavs.hw008.service.CRUD;

import ru.glavs.hw008.domain.projections.BookWithComments;

import java.util.List;

public interface BookCommentsCRUD {
    List<BookWithComments> readAllWithCommentsOnly();
    List<BookWithComments> readAll();
    List<BookWithComments> readBookByTitlePart(String titlePart);
    BookWithComments readBookById(String id);
}
