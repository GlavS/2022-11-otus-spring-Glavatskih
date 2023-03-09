//package ru.glavs.hw008.repository;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import ru.glavs.hw008.domain.projections.BookWithComments;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataMongoTest
//@DisplayName("Репозиторий книг должен")
//class BookRepositoryCustomImplTest {
//    @Autowired
//    private BookRepositoryCustomImpl repository;
//
//    @Test
//    @DisplayName("возвращать список книг, имеющих комментарии")
//    void repositoryShouldFindAllBooksWithCommentsOnly() {
//        List<BookWithComments> bookList = repository.findAllWithCommentsOnly();
//        assertThat(bookList).isNotNull().hasSize(2);
//    }
//
//    @Test
//    @DisplayName("возвращать список всех книг")
//    void repositoryShouldFindAllBooks() {
//        List<BookWithComments> bookList = repository.findAllWithComments();
//        assertThat(bookList).isNotNull().hasSize(3);
//    }
//
//    @Test
//    @DisplayName("возвращать список книг с комментариями по подстроке заглавия")
//    void repositoryShouldFindAllBooksWithCommentsByTitleContainingGivenSubstring() {
//        List<BookWithComments> bookList = repository.findAllWithCommentsByTitleContaining("Title1");
//        assertThat(bookList).isNotNull().hasSize(1);
//
//        bookList = repository.findAllWithCommentsByTitleContaining("Title");
//        assertThat(bookList).isNotNull().hasSize(3);
//    }
//
//    @Test
//    @DisplayName("возвращать книгу с комментарием по id")
//    void repositoryShouldReturnBookWithCommentByItsId(){
//        List<BookWithComments> bookList = repository.findAllWithComments();
//        String id = bookList.get(0).getId();
//        BookWithComments bookFound = repository.findBookWithCommentsById(id);
//        assertThat(bookFound).usingRecursiveComparison().isEqualTo(bookList.get(0));
//    }
//}