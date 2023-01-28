package ru.glavs.hw005.service.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.domain.Genre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CommentViewServiceTest {

    private static final Author FIRST_AUTHOR = new Author("Имя1", "Фамилия1", "А.А.");
    private static final Genre FIRST_GENRE = new Genre("Жанр1");
    private static final Book FIRST_BOOK = new Book(FIRST_AUTHOR, FIRST_GENRE, "Книга1");
    private static final Date FIRST_COMMENT_DATE;
    private static final Date SECOND_COMMENT_DATE;
    private static final Comment FIRST_COMMENT;
    private static final int FIRST_COMMENT_ID = 1;
    private static final Comment SECOND_COMMENT;
    private static final Comment NEW_COMMENT;
    private static final List<Comment> COMMENT_LIST;
    private static final Date THIRD_COMMENT_DATE;

    static {
        try {
            FIRST_COMMENT_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-19");
            SECOND_COMMENT_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-17");
            THIRD_COMMENT_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-21");
            FIRST_COMMENT = new Comment("Comment1, comment1 comment1 comment1 comment1.", "commentator1", FIRST_COMMENT_DATE, FIRST_BOOK);
            SECOND_COMMENT = new Comment("Comment2, comment2 comment2 comment2 comment2.", "commentator2", SECOND_COMMENT_DATE, FIRST_BOOK);
            NEW_COMMENT = new Comment("Comment3, comment3 comment3 comment3 comment3.", "commentator3", THIRD_COMMENT_DATE, FIRST_BOOK);
            COMMENT_LIST = List.of(FIRST_COMMENT, SECOND_COMMENT);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + e.getMessage(), e);
        }
    }

    @Autowired
    private AbstractViewService<Comment> service;

    @Test
    void displayItem() {
        String longComment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Mauris id nulla porta, vehicula velit non, porttitor sapien. Proin lobortis" +
                " magna vel vestibulum blandit. Suspendisse gravida tincidunt vestibulum. " +
                "Morbi vestibulum finibus libero non pharetra. Aliquam tellus odio, auctor ac " +
                "leo ut, semper venenatis ligula. Nunc id. ";
        List<Comment> commentList = List.of(new Comment(longComment, "Nuck", FIRST_COMMENT_DATE, FIRST_BOOK), new Comment(longComment, "Nack", FIRST_COMMENT_DATE, FIRST_BOOK));
//        System.out.println(FIRST_COMMENT);
//        service.displayItem(new Comment(4, longComment, "Nuck", FIRST_COMMENT_DATE));
//        System.out.println();
        service.printOne(new Comment(longComment, "Nuck", FIRST_COMMENT_DATE, FIRST_BOOK));
        System.out.println();
        service.printList(commentList);
    }

}