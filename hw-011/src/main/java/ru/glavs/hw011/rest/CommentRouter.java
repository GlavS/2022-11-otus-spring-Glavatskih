package ru.glavs.hw011.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.glavs.hw011.domain.Comment;
import ru.glavs.hw011.repository.CommentRepository;
import ru.glavs.hw011.rest.dto.CommentDto;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Configuration
@RequiredArgsConstructor
public class CommentRouter {

    private final CommentRepository commentRepository;

    @Bean
    public RouterFunction<ServerResponse> commentRoutes() {
        return route()
                .POST("/api/comments",
                        request -> request.bodyToMono(CommentDto.class)
                                .flatMap(commentDto -> Mono.just(new Comment(
                                        null,
                                        commentDto.getText(),
                                        commentDto.getAuthorNick(),
                                        stringToDate(commentDto.getDate()),
                                        commentDto.getCommentedBook())
                                ))
                                .flatMap(commentRepository::save)
                                .flatMap(c -> created(URI.create("/api/comments/" + c.getId())).body(fromValue(c)))
                ).build();

    }


    private Date stringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            parsedDate = format.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parsedDate;
    }


}
