package ru.glavs.hw008.repository;

import reactor.core.publisher.Flux;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    Flux<Comment> updateComments(Book book);
}
