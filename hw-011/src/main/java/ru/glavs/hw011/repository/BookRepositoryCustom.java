package ru.glavs.hw011.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.glavs.hw011.domain.projections.BookWithComments;

public interface BookRepositoryCustom {
    Flux<BookWithComments> findAllWithComments();

    Flux<BookWithComments> findAllWithCommentsOnly();

    Flux<BookWithComments> findAllWithCommentsByTitleContaining(String titlePart);

    Mono<BookWithComments> findBookWithCommentsById(String id);
}
