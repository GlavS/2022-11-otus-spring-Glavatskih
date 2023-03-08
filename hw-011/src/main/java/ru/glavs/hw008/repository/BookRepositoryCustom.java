package ru.glavs.hw008.repository;

import ru.glavs.hw008.domain.projections.BookWithComments;

import java.util.List;

public interface BookRepositoryCustom {
    List<BookWithComments> findAllWithComments();
    List<BookWithComments> findAllWithCommentsOnly();
    List<BookWithComments> findAllWithCommentsByTitleContaining(String titlePart);
    BookWithComments findBookWithCommentsById(String id);
}
