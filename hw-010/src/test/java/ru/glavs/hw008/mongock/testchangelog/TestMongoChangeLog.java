package ru.glavs.hw008.mongock.testchangelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.repository.AuthorRepository;
import ru.glavs.hw008.repository.BookRepository;
import ru.glavs.hw008.repository.CommentRepository;
import ru.glavs.hw008.repository.GenreRepository;

import java.util.Date;
import java.util.List;

@ChangeLog
public class TestMongoChangeLog {
    private Author author1;
    private Author author2;
    private Genre genre1;
    private Genre genre2;
    private Book book1;
    private Book book2;
    private Book book3;
    private Comment comment1;
    private Comment comment2;
    private Comment comment3;

    @ChangeSet(order = "001", id = "dropDB", author = "GlavS", runAlways = true)
    public void dropDB(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "GlavS", runAlways = true)
    public void insertAuthors(AuthorRepository repository) {
        author1 = repository.save(new Author("Name1", "Surname1", "A.A."));
        author2 = repository.save(new Author("Name2", "Surname2", "B.B."));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "GlavS", runAlways = true)
    public void insertGenres(GenreRepository repository) {
        genre1 = repository.save(new Genre("Genre1"));
        genre2 = repository.save(new Genre("Genre2"));
    }

    @ChangeSet(order = "004", id = "insetBooks", author = "GlavS", runAlways = true)
    public void insertBooks(BookRepository repository) {
        List<Author> authorList = List.of(author1, author2);
        List<Genre> genreList = List.of(genre1, genre2);
        book1 = repository.save(new Book(authorList, genreList, "Title1"));
        book2 = repository.save(new Book(authorList, genreList, "Title2"));
        book3 = repository.save(new Book(authorList, genreList, "Title3"));
    }

    @ChangeSet(order = "005", id = "insertComments", author = "GlavS", runAlways = true)
    public void insertComments(CommentRepository repository) {
        comment1 = repository.save(new Comment("Comment 1 test text test text test text",
                "Nick1",
                new Date(),
                book1));
        comment2 = repository.save(new Comment("Comment 2 test text test text test text",
                "Nick2",
                new Date(),
                book1));
        comment3 = repository.save(new Comment("Comment 3 test text test text test text",
                "Nick3",
                new Date(),
                book2));
    }
}
