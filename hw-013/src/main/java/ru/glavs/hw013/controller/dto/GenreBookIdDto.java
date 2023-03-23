package ru.glavs.hw013.controller.dto;

import lombok.Data;
import ru.glavs.hw013.domain.Genre;

@Data
public class GenreBookIdDto {
    private String name;
    private long bookId;

    public Genre toGenre() {
        return new Genre(name);
    }
}
