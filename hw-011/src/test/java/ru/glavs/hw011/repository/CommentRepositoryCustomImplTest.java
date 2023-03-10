//package ru.glavs.hw008.repository;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.annotation.DirtiesContext;
//import ru.glavs.hw008.domain.Book;
//import ru.glavs.hw008.domain.Comment;
//import ru.glavs.hw008.domain.Genre;
//import ru.glavs.hw008.domain.projections.BookWithComments;
//import ru.glavs.hw008.service.CRUD.BookCRUDImpl;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataMongoTest
//@DisplayName("Репозиторий комментариев должен")
//@ComponentScan("ru.glavs.hw008.service.CRUD")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//class CommentRepositoryCustomImplTest {
//
//    @Autowired
//    private BookCRUDImpl bookCRUD;
//
//    @Autowired
//    private CommentRepository repository;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Test
//    @DisplayName("Обновлять внедренную в комментарии книгу при обновлении основной сущности")
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void updateCommentsMethodShouldCascadeUpdateEmbeddedBook() {
//        BookWithComments bookWithComments = bookRepository.findAllWithCommentsByTitleContaining("Title1").get(0);
//        Book bookToUpdate = bookRepository.findById(bookWithComments.getId()).orElseThrow();
//        bookToUpdate.setTitle("Название1");
//        List<Genre> genreList = bookToUpdate.getGenres();
//        genreList.get(1).setName("Жанр2");
//        bookToUpdate.setGenres(genreList);
//        Book updatedBook = bookCRUD.save(bookToUpdate);
//
//        List<Comment> commentList = repository.findAllByCommentedBookId(updatedBook.getId());
//        for (Comment c : commentList) {
//            assertThat(c.getCommentedBook().getTitle()).isEqualTo("Название1");
//            assertThat(c.getCommentedBook().getGenres().get(1).getName()).isEqualTo("Жанр2");
//        }
//
//    }
//}