package ru.glavs.hw011.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Book;
import ru.glavs.hw011.domain.projections.BookWithComments;
import ru.glavs.hw011.repository.BookRepository;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("В роутере BookRouter")
class BookRouterTest {

    @Qualifier("booksRouterFunction")
    @Autowired
    private RouterFunction<ServerResponse> route;

    @Autowired
    private BookRepository repository;
    private List<Book> bookList;
    private Book book;
    private WebTestClient client;

    @BeforeEach
    void init() {
        client = WebTestClient.bindToRouterFunction(route).build();
        bookList = repository.findAll().collectList().block();
        assert bookList != null;
        book = bookList.get(0);
    }

    @Test
    @DisplayName("get-запрос эндпойнта 'books' должен вернуть список всех книг")
    void getApiBooksShouldReturnExpectedBookList() {
        client.get()
                .uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .hasSize(4)
                .value(list -> assertThat(list)
                        .usingRecursiveComparison()
                        .isEqualTo(bookList));
    }

    @Test
    @DisplayName("get-запрос эндпойнта 'books' с параметром должен вернуть книгу по ее id")
    void getApiBooksByIdShouldReturnBookById() {
        List<BookWithComments> bookWithComments = Objects
                .requireNonNull(repository
                        .findBookWithCommentsById(book.getId())
                        .collectList()
                        .block());
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/books")
                        .queryParam("id", book.getId())
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(BookWithComments.class)
                .value(b -> assertThat(b).usingRecursiveComparison().isEqualTo(bookWithComments));
    }

    @Test
    @DisplayName("patch-запрос эндпойнта 'books' должен сохранить книгу")
    void patchApiBooksShouldSaveABook() {
        client.patch()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("post-запрос эндпойнта 'books' должен сохранить новую книгу")
    void postApiBooksShouldSaveNewBook() {
        client.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("delete-запрос эндпойнта 'books' должен удалить книгу")
    void deleteApiBooksShouldDeleteBook() {
        Book forDelete = new Book("123", null, null, null);
        repository.save(forDelete).block();
        client.delete()
                .uri(uriBuilder -> uriBuilder.path("/api/books/{id}").build("123"))
                .exchange()
                .expectStatus()
                .isOk();
        Book deleted = repository.findById("123").block();
        assertThat(deleted).isNull();
    }
}