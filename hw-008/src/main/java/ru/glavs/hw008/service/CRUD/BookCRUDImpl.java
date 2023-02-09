package ru.glavs.hw008.service.CRUD;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.repository.BookRepository;

import java.util.List;
@Service
public class BookCRUDImpl implements BookCRUD {

    private final BookRepository repository;

    public BookCRUDImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public List<Book> readAll() {
        return null;
    }

    @Override
    public List<Book> readAllWithCommentsOnly() {
        return null;
    }

    @Override
    public Book readBook(long id) {
        return null;
    }

    @Override
    public void deleteById(ObjectId id) {
        repository.deleteById(id);
        //TODO: cascade delete commnets. Transactional!!
    }
}
