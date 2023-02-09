package ru.glavs.hw008.service.CRUD;

import org.bson.types.ObjectId;
import ru.glavs.hw008.domain.Book;

import java.util.List;

public interface BookCRUD {
    void save(Book book);

    List<Book> readAll();

    List<Book> readAllWithCommentsOnly();

    Book readBook(ObjectId id);

    void deleteById(ObjectId id);
}
