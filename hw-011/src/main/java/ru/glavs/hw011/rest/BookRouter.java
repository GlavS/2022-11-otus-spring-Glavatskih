package ru.glavs.hw011.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.projections.BookWithComments;
import ru.glavs.hw011.repository.BookRepository;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> booksRouterFunction(BookRepository repository){
        return route()
                .GET("/api/books",
                        books -> ok().body(repository.findAllWithComments(), BookWithComments.class)).build();
    }
}
