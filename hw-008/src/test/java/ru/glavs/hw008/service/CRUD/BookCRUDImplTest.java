package ru.glavs.hw008.service.CRUD;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.repository.AuthorRepository;
import ru.glavs.hw008.repository.BookRepository;
import ru.glavs.hw008.repository.CommentRepository;
import ru.glavs.hw008.repository.GenreRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@DisplayName("Сервис книг должен")
@ComponentScan("ru.glavs.hw008.service.CRUD")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BookCRUDImplTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCRUDImpl bookService;
    private final Genre newGenre = new Genre("Genre3");
    private final Author newAuthor = new Author("Name3", "Surname3", "C.C.");
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("каскадно сохранять список авторов и жанров в свои коллекции")
    void shouldSaveAuthorsAndGenresToRespectiveCollectionsWhenSavingBook() {
        Book savedBook = bookService.save(new Book(List.of(newAuthor), List.of(newGenre), "Title 4"));
        List<Author> savedAuthor = authorRepository.findBySurnameContainingIgnoreCase("Surname3");
        List<Genre> savedGenre = genreRepository.findByNameContainingIgnoreCase("Genre3");
        assertAll(
                () -> assertThat(savedAuthor).hasSize(1),
                () -> assertThat(savedAuthor.get(0).getName()).isEqualTo("Name3"),
                () -> assertThat(savedGenre).hasSize(1),
                () -> assertThat(savedGenre.get(0).getName()).isEqualTo("Genre3")
        );
        assertAll(
                () -> assertThat(savedBook.getAuthors()).hasSize(1),
                () -> assertThat(savedBook.getGenres()).hasSize(1)
        );
    }

    @Test
    @DisplayName("каскадно удалять комментарии, относящиеся к книге")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteCommentsReferringDeletedBook() {
        List<BookWithComments> booksWithComments = bookRepository.findAllWithCommentsByTitleContaining("Title1");
        List<Comment> commentList = booksWithComments.get(0).getComments();
        Comment comment1 = commentRepository.findById(commentList.get(0).getId()).orElseThrow();
        Comment comment2 = commentRepository.findById(commentList.get(1).getId()).orElseThrow();

        assertThat(comment1.getText()).contains("Comment 1");
        assertThat(comment2.getText()).contains("Comment 2");


        bookService.delete(booksWithComments.get(0));

        assertThat(bookRepository.findAllWithCommentsByTitleContaining("Title1")).isEmpty();
        assertThrows(NoSuchElementException.class, () -> commentRepository.findById(commentList.get(0).getId()).orElseThrow());
        assertThrows(NoSuchElementException.class, () -> commentRepository.findById(commentList.get(1).getId()).orElseThrow());

    }
}