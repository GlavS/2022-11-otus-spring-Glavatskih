package ru.glavs.hw014.batch.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.glavs.hw014.domain.Author;
import ru.glavs.hw014.domain.Genre;

import javax.persistence.Id;
import java.util.List;

@Document
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MongoBook {
    @Id
    private ObjectId id;
    private Author author;
    private Genre genre;
    private String title;
    private List<MongoComment> comments;
}
