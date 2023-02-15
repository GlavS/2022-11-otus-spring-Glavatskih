package ru.glavs.hw008.domain.projections;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class BookWithComments {
    private ObjectId id;
    private String title;
    private List<Author> authors;
    private List<Genre> genres;
    private List<Comment> comments;

    public static Book toBook(BookWithComments bookWithComments){
        return new Book(bookWithComments.getId(),
                bookWithComments.getAuthors(),
                bookWithComments.getGenres(),
                bookWithComments.getTitle());
    }
}
