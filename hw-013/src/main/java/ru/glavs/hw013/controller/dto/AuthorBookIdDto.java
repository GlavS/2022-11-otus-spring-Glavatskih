package ru.glavs.hw013.controller.dto;

import lombok.Data;
import ru.glavs.hw013.domain.Author;

@Data
public class AuthorBookIdDto {
    private String name;
    private String surname;
    private String initials;
    private long bookId;

    public Author toAuthor() {
        return new Author(name, surname, initials);
    }
}
