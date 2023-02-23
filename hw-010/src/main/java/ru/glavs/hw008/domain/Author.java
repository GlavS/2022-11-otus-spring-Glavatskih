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
@Document(collection = "authors")
public class Author {
    @Id
    private String id;
    private String name;
    private String surname;
    private String initials;

    public Author(String name, String surname, String initials) {
        this.name = name;
        this.surname = surname;
        this.initials = initials;
    }
}
