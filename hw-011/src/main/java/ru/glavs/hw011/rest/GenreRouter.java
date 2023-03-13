package ru.glavs.hw011.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Genre;
import ru.glavs.hw011.repository.GenreRepository;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Configuration
@RequiredArgsConstructor
public class GenreRouter {

    private final GenreRepository repository;

    @Bean
    public RouterFunction<ServerResponse> genreRoutes() {
        return route()
                .POST("/api/genres", request -> request
                        .bodyToMono(Genre.class)
                        .flatMap(repository::save)
                        .flatMap(savedGenre -> created(URI.create("/api/genres" + savedGenre.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .build()))

                .GET("/api/genres/{id}", accept(APPLICATION_JSON), request -> repository.findById(request.pathVariable("id"))
                        .flatMap(genre -> ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(genre)))
                        .switchIfEmpty(notFound().build()))

                .GET("/api/genres", genres -> ok()
                        .body(repository.findAll(), Genre.class))
                .build();
    }
}
