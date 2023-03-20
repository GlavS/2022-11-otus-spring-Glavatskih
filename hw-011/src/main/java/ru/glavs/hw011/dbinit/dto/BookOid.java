package ru.glavs.hw011.dbinit.dto;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class BookOid {
    private ObjectId _id;
    private List<AuthorOid> authors;
    private List<GenreOid> genres;
    private String title;
}
