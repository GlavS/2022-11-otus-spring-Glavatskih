package ru.glavs.hw008.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "books")
public class Book {
    @Id
    private ObjectId id;
    private List<Author> authors;
    private List<Genre> genres;
    private String title;

    public Book(List<Author> author, List<Genre> genre, String title) {
        this.authors = author;
        this.genres = genre;
        this.title = title;
    }
}
