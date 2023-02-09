package ru.glavs.hw008.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "books")
public class Book {
    @Id
    private ObjectId id;
    private List<Author> authors;
    private List<Genre> genres;
    private String title;

    public Book(List<Author> authors, List<Genre> genres, String title) {
        this.authors = authors;
        this.genres = genres;
        this.title = title;
    }
}
