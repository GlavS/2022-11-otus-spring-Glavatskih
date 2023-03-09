package ru.glavs.hw008.dbinit;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.glavs.hw008.dbinit.dto.AuthorOid;
import ru.glavs.hw008.dbinit.dto.BookOid;
import ru.glavs.hw008.dbinit.dto.CommentOid;
import ru.glavs.hw008.dbinit.dto.GenreOid;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.mongock.jsondata.JsonFileLoader;
import ru.glavs.hw008.repository.AuthorRepository;
import ru.glavs.hw008.repository.BookRepository;
import ru.glavs.hw008.repository.CommentRepository;
import ru.glavs.hw008.repository.GenreRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseInitializer implements ApplicationRunner {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final ObjectMapper mapper;
    private final JsonFileLoader loader;

    private static final String BOOKS_JSON_RESOURCE = "mongock/books.json";
    private static final String AUTHOR_JSON_RESOURCE = "mongock/authors.json";
    private static final String GENRE_JSON_RESOURCE = "mongock/genres.json";
    private static final String COMMENT_JSON_RESOURCE = "mongock/comments.json";


    public DatabaseInitializer(AuthorRepository authorRepository,
                               GenreRepository genreRepository,
                               BookRepository bookRepository,
                               CommentRepository commentRepository,
                               ObjectMapper mapper,
                               JsonFileLoader loader) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
        this.loader = loader;

        SimpleModule module = new SimpleModule("ObjectID",
                new Version(1,
                        0,
                        0,
                        null,
                        null,
                        null));
        module.addDeserializer(ObjectId.class, new ObjectIDDeserializer());
        module.addDeserializer(Date.class, new DateDeserializer());
        this.mapper.registerModule(module);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        insertAuthors();
        insertGenres();
        insertBooks();
        insertComments();
    }

    private void insertAuthors() throws Exception {
        loader.setJsonFile(AUTHOR_JSON_RESOURCE);
        String authors = loader.getRawData();
        List<AuthorOid> authorList = mapper.readValue(authors, new TypeReference<>() {
        });
        List<Author> convertedAuthorsList = authorList.stream()
                .map(authorOid -> new Author(authorOid.get_id().toString(),
                        authorOid.getName(),
                        authorOid.getSurname(),
                        authorOid.getInitials())).collect(Collectors.toList());
        authorRepository.insert(Flux.fromIterable(convertedAuthorsList)).subscribe();
    }

    private void insertGenres() throws Exception {
        loader.setJsonFile(GENRE_JSON_RESOURCE);
        String genres = loader.getRawData();
        List<GenreOid> genreList = mapper.readValue(genres, new TypeReference<>() {
        });
        List<Genre> convertedGenreList = genreList.stream()
                .map(genreOid -> new Genre(
                        genreOid.get_id().toString(),
                        genreOid.getName()
                )).collect(Collectors.toList());
        genreRepository.insert(Flux.fromIterable(convertedGenreList)).subscribe();
    }

    private void insertBooks() throws Exception {
        loader.setJsonFile(BOOKS_JSON_RESOURCE);
        String books = loader.getRawData();
        List<BookOid> bookList = mapper.readValue(books, new TypeReference<>() {
        });

        List<Book> convertedBookList = bookList.stream()
                .map(bookOid -> new Book(
                        bookOid.get_id().toString(),
                        bookOid.getAuthors().stream()
                                .map(authorOid -> new Author(authorOid.get_id().toString(),
                                        authorOid.getName(),
                                        authorOid.getSurname(),
                                        authorOid.getInitials())).collect(Collectors.toList()),
                        bookOid.getGenres().stream()
                                .map(genreOid -> new Genre(genreOid.get_id().toString(),
                                        genreOid.getName())).collect(Collectors.toList()),
                        bookOid.getTitle()
                )).collect(Collectors.toList());
        bookRepository.insert(Flux.fromIterable(convertedBookList)).subscribe();
    }

    private void insertComments() throws Exception {
        loader.setJsonFile(COMMENT_JSON_RESOURCE);
        String comments = loader.getRawData();

        List<CommentOid> commentList = mapper.readValue(comments, new TypeReference<>() {
        });

        List<Comment> convertedCommentList = commentList.stream()
                .map(commentOid -> new Comment(commentOid.get_id().toString(),
                        commentOid.getText(),
                        commentOid.getAuthorNick(),
                        commentOid.getDate(),
                        new Book(
                                commentOid.getCommentedBook().get_id().toString(),
                                commentOid.getCommentedBook().getAuthors().stream()
                                        .map(authorOid -> new Author(authorOid.get_id().toString(),
                                                authorOid.getName(),
                                                authorOid.getSurname(),
                                                authorOid.getInitials())).collect(Collectors.toList()),
                                commentOid.getCommentedBook().getGenres().stream()
                                        .map(genreOid -> new Genre(genreOid.get_id().toString(),
                                                genreOid.getName())).collect(Collectors.toList()),
                                commentOid.getCommentedBook().getTitle()
                        )
                )).collect(Collectors.toList());
        commentRepository.insert(Flux.fromIterable(convertedCommentList)).subscribe();
    }
}
