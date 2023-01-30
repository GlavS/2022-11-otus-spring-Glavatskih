package ru.glavs.hw006.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw006.domain.Book;
import ru.glavs.hw006.domain.Comment;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({CommentDaoImpl.class, BookDaoImpl.class})
@DisplayName("Класс CommentDaoImpl должен")
class CommentDaoImplTest {

    private static final long FIRST_COMMENT_ID = 1;
    private static final Date THIRD_COMMENT_DATE;

    static {
        try {
            THIRD_COMMENT_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-21");
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
        Book commentedBook = em.find(Book.class, 1L);
        dao.save(new Comment(
                "Comment3, comment3 comment3 comment3 comment3.",
                "commentator3",
                THIRD_COMMENT_DATE,
                commentedBook
        ));
        em.flush();
        Comment comment3 = em.find(Comment.class, 3L);
        assertThat(comment3.getText()).isEqualTo(
                "Comment3, comment3 comment3 comment3 comment3."
        );
    }

    @Test
    @DisplayName("возвращать комментарий по его id")
    void getByIdMethodShouldReturnCommentByItsID() {
        Comment comment = dao.getById(FIRST_COMMENT_ID);
        assertThat(comment.getText()).isEqualTo("Comment1, comment1 comment1 comment1 comment1.");

    }

    @Test
    @DisplayName("удалять комментарий")
    void deleteMethodShouldDeleteComment() {
        Comment commentToDelete = em.find(Comment.class, FIRST_COMMENT_ID);
        dao.delete(commentToDelete);
        em.flush();
        assertThatThrownBy(() -> dao.getById(FIRST_COMMENT_ID)).isInstanceOf(NoSuchElementException.class);
    }
}