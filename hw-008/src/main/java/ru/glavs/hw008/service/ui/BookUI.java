package ru.glavs.hw008.service.ui;

import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.projections.BookWithComments;

public interface BookUI {
    Book create();

    Book update(Book book);

    BookWithComments pickByTitlePart(String titlePart);
}
