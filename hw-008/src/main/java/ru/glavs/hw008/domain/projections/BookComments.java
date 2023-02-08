package ru.glavs.hw008.domain.projections;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class BookComments {
    private String title;
    private List<Author> authors;
    private List<Genre> genres;
    private List<Comment> comments;
}
