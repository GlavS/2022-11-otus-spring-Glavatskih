package ru.glavs.hw008.repository;

import ru.glavs.hw008.domain.projections.BookComments;

import java.util.List;

public interface BookRepositoryCustom {
    List<BookComments> findAllWithComments();
    List<BookComments> findAllWithCommentsOnly();
}
