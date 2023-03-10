package ru.glavs.hw011.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Author;
import ru.glavs.hw011.repository.AuthorRepository;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class AuthorRouter {
    @Bean
    public RouterFunction<ServerResponse> routes(AuthorRepository repository) {
        return route()
                .POST("/authors", request -> request.bodyToMono(Author.class)
                        .flatMap(repository::save)
                        .flatMap(savedAuthor -> created(URI.create("/authors/" + savedAuthor.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .build())
                )


                .GET("/authors", a ->
                        ok().body(repository.findAll(), Author.class)).build();
    }
}
