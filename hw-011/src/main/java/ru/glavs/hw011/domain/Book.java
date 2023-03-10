package ru.glavs.hw011.domain;

import lombok.*;
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
    private String id;
    private List<Author> authors;
    private List<Genre> genres;
    private String title;

    public Book(List<Author> authors, List<Genre> genres, String title) {
        this.authors = authors;
        this.genres = genres;
        this.title = title;
    }
}
