package ru.glavs.hw011.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Book;
import ru.glavs.hw011.domain.projections.BookWithComments;
import ru.glavs.hw011.repository.BookRepository;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Configuration
@RequiredArgsConstructor
public class BookRouter {
    private final BookRepository bookRepository;

    @Bean
    public RouterFunction<ServerResponse> booksRouterFunction() {
        return route()
                .GET("/api/books",
                        queryParam("id", StringUtils::isNotEmpty),
                        request -> request.queryParam("id")
                                .map(bookRepository::findBookWithCommentsById)
                                .map(book -> ok()
                                        .body(book, BookWithComments.class))
                                .orElse(badRequest().build()))
                .GET("/api/books",
                        books -> ok()
                                .contentType(APPLICATION_JSON)
                                .body(bookRepository.findAllWithComments(), BookWithComments.class))
                .PATCH("/api/books",
                        request -> request.bodyToMono(Book.class)
                                .flatMap(book -> created(URI.create("/api/books/" + book.getId()))
                                        .body(bookRepository.save(book), Book.class))
                                .switchIfEmpty(badRequest().build()))
                .POST("/api/books",
                        request -> request.bodyToMono(Book.class)
                                .flatMap(book -> created(URI.create("/api/books/" + book.getId()))
                                        .body(bookRepository.save(book), Book.class))
                                .switchIfEmpty(badRequest().build()))
                .DELETE("/api/books/{id}", accept(APPLICATION_JSON),
                        request -> bookRepository.deleteById(request.pathVariable("id"))
                                .flatMap(resp -> ok().build())
                ).build();
    }
}
