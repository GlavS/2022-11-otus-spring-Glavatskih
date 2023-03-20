package ru.glavs.hw011.rest.dto;

import lombok.Data;
import ru.glavs.hw011.domain.Book;


@Data
public class CommentDto {
    private String id;
    private String text;
    private String authorNick;
    private String date;
    private Book commentedBook;
}
