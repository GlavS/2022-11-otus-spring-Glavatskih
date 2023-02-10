package ru.glavs.hw008.service.CRUD;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.repository.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookCRUDImpl implements BookCRUD {

    private final BookRepository repository;
    private final AuthorCRUD authorCRUD;
    private final GenreCRUD genreCRUD;
    private final CommentCRUD commentCRUD;


    @Override
    @Transactional
    public void save(Book book) {
        List<Author> authorList = book.getAuthors();
        List<Genre> genreList = book.getGenres();
        authorCRUD.saveAll(authorList);
        genreCRUD.saveAll(genreList);
        repository.save(book);
    }

    @Override
    @Transactional
    public void delete(BookWithComments bookWithComments) {
        List<Comment> commentList = bookWithComments.getComments();
        commentCRUD.deleteAll(commentList);
        repository.delete(BookWithComments.toBook(bookWithComments));
    }
}
