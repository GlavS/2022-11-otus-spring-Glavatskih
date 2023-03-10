package ru.glavs.hw011.repository;

import reactor.core.publisher.Flux;
import ru.glavs.hw011.domain.Book;
import ru.glavs.hw011.domain.Comment;

public interface CommentRepositoryCustom {
    Flux<Comment> updateComments(Book book);
}
