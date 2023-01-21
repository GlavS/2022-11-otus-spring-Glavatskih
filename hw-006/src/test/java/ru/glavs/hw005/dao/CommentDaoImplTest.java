package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@DataJpaTest
@Import(CommentDaoImpl.class)
@DisplayName("Класс CommentDaoImpl должен")
class CommentDaoImplTest {

    @Autowired
    private CommentDaoImpl dao;

    private static final Comment FIRST_COMMENT;
    private static final Comment SECOND_COMMENT;

    static {
        try {
            FIRST_COMMENT = new Comment(1, "Comment1, comment1 comment1 comment1 comment1.", "commentator1", new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-19"));
            SECOND_COMMENT = new Comment(2, "Comment2, comment2 comment2 comment2 comment2.", "commentator2", new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-17"));
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + e.getMessage(), e);
        }
    }



    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void getCommentsForBook() {
    }
}