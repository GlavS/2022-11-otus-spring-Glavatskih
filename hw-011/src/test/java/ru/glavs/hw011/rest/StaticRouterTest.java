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
import ru.glavs.hw011.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Роутер статического контента должен")
class StaticRouterTest {

    @Autowired
    @Qualifier("staticPages")
    private RouterFunction<ServerResponse> route;

    @Autowired
    private BookRepository repository;

    private WebTestClient client;
    private Book book;

    @BeforeEach
    void init() {
        client = WebTestClient.bindToRouterFunction(route).build();
        List<Book> bookList = repository.findAll().collectList().block();
        assert bookList != null;
        book = bookList.get(0);
    }

    @Test
    @DisplayName("правильно отображать страницу списка книг")
    void staticRootPageRouteShouldCorrectlyDisplayRootPage() {
        client.get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.TEXT_HTML)
                .expectBody()
                .consumeWith(responseText ->
                        assertThat(responseText.toString()).contains("<title>Book list</title>"));
    }

    @Test
    @DisplayName("правильно отображать страницу показа одной книги")
    void staticShowPageRouteShouldCorrectlyDisplayOneBookShowPage() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/book-show").queryParam("id", book.getId()).build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.TEXT_HTML)
                .expectBody()
                .consumeWith(responseText ->
                        assertThat(responseText.toString()).contains("<title id=\"pageTitle\">Boris Godunov</title>"));
    }
}