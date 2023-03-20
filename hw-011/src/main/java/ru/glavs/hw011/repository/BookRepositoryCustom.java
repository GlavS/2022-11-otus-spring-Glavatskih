package ru.glavs.hw011.repository;

import reactor.core.publisher.Flux;
import ru.glavs.hw011.domain.projections.BookWithComments;

public interface BookRepositoryCustom {
    Flux<BookWithComments> findAllWithComments();


    Flux<BookWithComments> findBookWithCommentsById(String id);
}
