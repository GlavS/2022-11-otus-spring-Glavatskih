package ru.glavs.hw011.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Author;
import ru.glavs.hw011.repository.AuthorRepository;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("В роутере AuthorRouter")
class AuthorRouterTest {

    @Qualifier("authorRoutes")
    @Autowired
    private RouterFunction<ServerResponse> route;

    @Autowired
    private AuthorRepository repository;
    private Author author;
    private WebTestClient client;

    @BeforeEach
    void init() {
        client = WebTestClient
                .bindToRouterFunction(route)
                .build();
        List<Author> authorList = repository.findAll().collectList().block();
        assert authorList != null;
        author = authorList.get(0);
    }

    @Test
    @DisplayName("get-запрос эндпойнта 'authors' должен вернуть список авторов")
    void routeGetAuthorsShouldReturnExpectedAuthorsList() {
        client.get()
                .uri("/api/authors")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Author.class)
                .hasSize(2);
    }

    @Test
    @DisplayName("get-запрос эндпойнта 'authors{id}' должен вернуть правильного автора")
    void routeGetAuthorsByIdShouldReturnExpectedAuthor() {

        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/authors/{id}")
                        .build(author.getId()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Author.class)
                .consumeWith(b -> assertThat(Objects.requireNonNull(b.getResponseBody()).getName()).isEqualTo(author.getName()));
    }

     @Test
     @DisplayName("post-запрос эндпойнта '/api/authors' должен сохранить автора")
     @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
     void routes(){
        Author forSave = new Author("123", "Surname-Save", "Name-Save", "I.I.");
        client.post()
                .uri("/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(forSave)
                .exchange()
                .expectStatus()
                .isCreated();
    }
}