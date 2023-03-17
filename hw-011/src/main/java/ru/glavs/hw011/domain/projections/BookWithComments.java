package ru.glavs.hw011.domain.projections;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.glavs.hw011.domain.Author;
import ru.glavs.hw011.domain.Comment;
import ru.glavs.hw011.domain.Genre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class BookWithComments {
    private String id;
    private String title;
    private List<Author> authors;
    private List<Genre> genres;
    private List<Comment> comments;
}
