package ru.glavs.hw011.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Genre;
import ru.glavs.hw011.repository.GenreRepository;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class GenreRouter {

    private final GenreRepository repository;

    @Bean
    public RouterFunction<ServerResponse> genreRoutes() {
        return route()
                .GET("/api/genres", genres -> ok().body(repository.findAll(), Genre.class)).build();
    }
}
