//package ru.glavs.hw008.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import ru.glavs.hw008.domain.Comment;
//import ru.glavs.hw008.domain.projections.BookWithComments;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataMongoTest
//@DisplayName("Репозиторий комментариев должен")
//class CommentRepositoryTest {
//    @Autowired
//    private CommentRepository repository;
//    @Autowired
//    private BookRepository bookRepository;
//    private BookWithComments book;
//
//    private static final int BOOK_WITH_TWO_COMMENTS_INDEX = 0;
//
//    @BeforeEach
//    void dataInit() {
//        book = bookRepository.findAllWithCommentsOnly().get(BOOK_WITH_TWO_COMMENTS_INDEX);
//
//    }
//
//    @Test
//    @DisplayName("Находить все комментарии к книге по её Id")
//    void shouldFindAllCommentsByBookId() {
//        List<Comment> commentList = repository.findAllByCommentedBookId(book.getId());
//        assertAll("comment",
//                () -> assertEquals("Title1", book.getTitle()),
//                () -> assertTrue(commentList.get(0).getText().contains("Comment 1"))
//        );
//        assertThat(commentList).isNotNull().hasSize(2);
//    }
//
//    @Test
//    @DisplayName("Находить комментарий по фрагменту текста")
//    void shouldFindCommentByContainingTextIgnoreCase() {
//        List<Comment> commentList = repository.findByTextContainingIgnoreCase("comment");
//        assertThat(commentList).hasSize(3);
//        commentList = repository.findByTextContainingIgnoreCase("comment 1");
//        assertThat(commentList).hasSize(1);
//    }
//}