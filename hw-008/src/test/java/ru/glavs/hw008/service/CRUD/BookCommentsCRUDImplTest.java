package ru.glavs.hw008.service.CRUD;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.glavs.hw008.domain.projections.BookWithComments;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan("ru.glavs.hw008.service.CRUD")
@DisplayName("Сервис книг с комментариями должен")
class BookCommentsCRUDImplTest {
    @Autowired
    private BookCommentsCRUDImpl bookCommentsCRUD;

    @Test
    @DisplayName("возвращать список книг с комментариями")
    void shouldReturnAllBooksHavingComments() {
        List<BookWithComments> bookList = bookCommentsCRUD.readAllWithCommentsOnly();
        assertThat(bookList).hasSize(2);

    }

    @Test
    @DisplayName("возвращать список всех книг, в том числе с комментариями")
    void readAllMethodShouldReturnListOfAllBooks() {
        List<BookWithComments> bookList = bookCommentsCRUD.readAll();
        assertThat(bookList).hasSize(3);
    }

    @Test
    @DisplayName("искать книгу по фрагменту заглавия")
    void readBookByTitlePartMethodShouldSearchBooksByTitleFragment() {
        List<BookWithComments> bookList = bookCommentsCRUD.readBookByTitlePart("Title3");
        assertThat(bookList).hasSize(1);
        bookList = bookCommentsCRUD.readBookByTitlePart("titl");
        assertThat(bookList).hasSize(3);
    }
}