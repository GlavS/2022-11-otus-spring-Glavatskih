package ru.glavs.hw008.rest.dto;

import lombok.Data;


@Data
public class CommentDto {
    private String id;
    private String text;
    private String authorNick;
    private String date;
    private String commentedBookId;
}
