package ru.glavs.hw008.service.CRUD;

import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.projections.BookWithComments;

public interface BookCRUD {
    void save(Book book);

    void delete(BookWithComments bookWithComments);
}
