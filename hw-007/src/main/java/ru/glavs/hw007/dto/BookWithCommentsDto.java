package ru.glavs.hw007.dto;

import lombok.*;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Comment;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookWithCommentsDto {
    private Book book;
    private List<Comment> commentList;

    public static BookWithCommentsDto fromDomainObject(Book book){
        List<Comment> commentsFromDomainObject = new ArrayList<>(book.getComments());
        return new BookWithCommentsDto(book, commentsFromDomainObject);
    }
}
