package ru.glavs.hw005.service.display;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.glavs.hw005.domain.Comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CommentDisplayServiceTest {
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
            FIRST_COMMENT = new Comment(1, "Comment1, comment1 comment1 comment1 comment1.", "commentator1", FIRST_COMMENT_DATE);
            SECOND_COMMENT = new Comment(2, "Comment2, comment2 comment2 comment2 comment2.", "commentator2", SECOND_COMMENT_DATE);
            NEW_COMMENT = new Comment(3, "Comment3, comment3 comment3 comment3 comment3.", "commentator3", THIRD_COMMENT_DATE);
            COMMENT_LIST = List.of(FIRST_COMMENT, SECOND_COMMENT);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + e.getMessage(), e);
        }
    }

    @Autowired
    private AbstractDisplayService<Comment> service;

    @Test
    void displayItem() {
        System.out.println(FIRST_COMMENT);
        service.displayItem(FIRST_COMMENT);
        System.out.println();
        service.printOne(FIRST_COMMENT);//TODO: run this

    }

}