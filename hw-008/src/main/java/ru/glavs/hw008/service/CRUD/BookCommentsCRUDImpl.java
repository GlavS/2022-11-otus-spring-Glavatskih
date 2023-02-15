package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.repository.BookRepository;

import java.util.List;

@Service
public class BookCommentsCRUDImpl implements BookCommentsCRUD {

    private final BookRepository bookRepository;

    public BookCommentsCRUDImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookWithComments> readAllWithCommentsOnly() {
        return bookRepository.findAllWithCommentsOnly();
    }

    @Override
    public List<BookWithComments> readAll() {
        return bookRepository.findAllWithComments();
    }

    @Override
    public List<BookWithComments> readBookByTitlePart(String titlePart) {
        return bookRepository.findAllWithCommentsByTitleContaining(titlePart);
    }
}
