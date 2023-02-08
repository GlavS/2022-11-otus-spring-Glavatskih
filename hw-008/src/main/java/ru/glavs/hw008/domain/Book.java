package ru.glavs.hw008.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "books")
public class Book {
    @Id
    private ObjectId id;
    private Author author;
    private Genre genre;
    private String title;

    public Book(Author author, Genre genre, String title) {
        this.author = author;
        this.genre = genre;
        this.title = title;
    }
}
