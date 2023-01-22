package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Comment;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({CommentDaoImpl.class, BookDaoImpl.class})
@DisplayName("Класс CommentDaoImpl должен")
class CommentDaoImplTest {


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
    private CommentDaoImpl dao;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("сохранять новый комментарий")
    void saveMethodShouldSaveCommentToDatabase() {
        dao.save(NEW_COMMENT);
        em.flush();
        Comment comment3 = em.find(Comment.class, 3);
        assertThat(comment3)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(NEW_COMMENT);
    }

    @Test
    @DisplayName("возвращать комментарий по его id")
    void getByIdMethodShouldReturnCommentByItsID() {
        Comment comment = dao.getById(FIRST_COMMENT_ID);
        assertThat(comment).usingRecursiveComparison().isEqualTo(FIRST_COMMENT);

    }

    @Test
    @DisplayName("удалять комментарий по его id")
    void deleteMethodShouldDeleteCommentByItsID() {
        dao.delete(FIRST_COMMENT_ID);
        em.flush();
        assertThatThrownBy(() -> dao.getById(FIRST_COMMENT_ID)).isInstanceOf(NoSuchElementException.class);
    }
}