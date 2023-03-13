package ru.glavs.hw011.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Author;
import ru.glavs.hw011.repository.AuthorRepository;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Configuration
public class AuthorRouter {
    @Bean
    public RouterFunction<ServerResponse> authorRoutes(AuthorRepository repository) {
        return route()
                .POST("/api/authors", request -> request.bodyToMono(Author.class)
                        .flatMap(repository::save)
                        .flatMap(savedAuthor -> created(URI.create("/api/authors/" + savedAuthor.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .build())
                )

                .GET("/api/authors/{id}",
                        accept(APPLICATION_JSON), request -> repository.findById(request.pathVariable("id"))
                        .flatMap(author -> ok().contentType(APPLICATION_JSON).body(fromValue(author)))
                        .switchIfEmpty(notFound().build()))

                .GET("/api/authors", a ->
                        ok().body(repository.findAll(), Author.class)).build();
    }
}
