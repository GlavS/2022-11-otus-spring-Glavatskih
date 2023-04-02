package ru.glavs.hw013.controller.dto;

import lombok.Data;
import ru.glavs.hw013.controller.CommentController;
import ru.glavs.hw013.domain.Comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CommentBookIdDto {
    private long id;
    private String text;
    private String authorNick;
    private String date;
    private long bookId;

    public Comment toComment() {
        Date dateParsed = parseDate(date);
        return new Comment(id, text, authorNick, dateParsed, null);
    }
    private static Date parseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateReceived;
        try {
            dateReceived = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse comment date: " + e.getMessage(), e);
        }
        return dateReceived;
    }
}
