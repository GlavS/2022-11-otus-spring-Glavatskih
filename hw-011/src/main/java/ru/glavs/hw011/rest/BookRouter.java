package ru.glavs.hw011.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Book;
import ru.glavs.hw011.domain.projections.BookWithComments;
import ru.glavs.hw011.repository.BookRepository;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

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
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(bookRepository.findAllWithComments(), BookWithComments.class))
                .PATCH("/api/books",
                        request -> request.bodyToMono(Book.class)
                                .flatMap(book -> ok()
                                        .body(bookRepository.save(book), Book.class))
                                .switchIfEmpty(badRequest().build()))
                .POST("/api/books",
                        request -> request.bodyToMono(Book.class)
                                .flatMap(book -> ok()
                                        .body(bookRepository.save(book), Book.class))
                                .switchIfEmpty(badRequest().build()))
                .DELETE("/api/books",
                        request -> request.queryParam("id")
                                .map(bookRepository::deleteById)
                                .map(voidMono -> ok()
                                        .bodyValue(Optional.empty()))
                                .orElse(badRequest().build())
                ).build();
    }
}
