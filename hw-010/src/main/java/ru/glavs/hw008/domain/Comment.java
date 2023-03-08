package ru.glavs.hw008.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String text;
    private String authorNick;
    private Date date;
    private Book commentedBook;

    public Comment(String text, String authorNick, Date date, Book commentedBook) {
        this.text = text;
        this.authorNick = authorNick;
        this.date = date;
        this.commentedBook = commentedBook;
    }
}
