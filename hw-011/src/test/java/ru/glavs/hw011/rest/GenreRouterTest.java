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
import ru.glavs.hw011.domain.Genre;
import ru.glavs.hw011.repository.GenreRepository;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@DisplayName("В роутере GenreRouter")
class GenreRouterTest {

    @Qualifier("genreRoutes")
    @Autowired
    private RouterFunction<ServerResponse> route;

    @Autowired
    private GenreRepository repository;
    private Genre genre;
    private WebTestClient client;

    @BeforeEach
    void init(){
        client = WebTestClient
                .bindToRouterFunction(route)
                .build();
        List<Genre> genreList = repository.findAll().collectList().block();
        assert genreList != null;
        genre = genreList.get(0);
    }

    @Test
    @DisplayName("get-запрос эндпойнта 'genres' должен вернуть список жанров")
    void routeGetApiGenresShouldReturnExpectedGenreList() {
        client.get()
                .uri("/api/genres")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Genre.class)
                .hasSize(2)
                .consumeWith(list->
                        assertThat(Objects.requireNonNull(list.getResponseBody())
                                .get(0))
                                .usingRecursiveComparison()
                                .isEqualTo(genre));
    }

    @Test
    @DisplayName("get-запрос эндпойнта 'genres{id}' должен вернуть жанр по id")
    void routeGetApiGenresByIdShouldReturnExpectedGenre() {
        client.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/api/genres/{id}")
                                .build(genre.getId()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Genre.class)
                .value(g->
                        assertThat(g)
                                .usingRecursiveComparison()
                                .isEqualTo(genre));
    }

    @Test
    @DisplayName("post-запрос эндпойнта 'genres' должен сохранить жанр")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void routePostApiGenresShouldSaveGenre() {
        Genre forSave = new Genre("123", "Genre-Save");
        client.post()
                .uri("/api/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(forSave)
                .exchange()
                .expectStatus()
                .isCreated();
    }
}