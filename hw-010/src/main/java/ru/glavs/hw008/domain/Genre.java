package ru.glavs.hw008.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "genres")
public class Genre {
    @Id
    private String id;
    private String name;

    public Genre(String genre) {
        this.name = genre;
    }
}
