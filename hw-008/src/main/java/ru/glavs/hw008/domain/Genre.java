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
@Document(collection = "genres")
public class Genre {
    @Id
    private ObjectId id;
    private String name;

    public Genre(String genre) {
        this.name = genre;
    }
}
