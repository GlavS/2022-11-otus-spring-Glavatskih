package ru.glavs.hw011.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.glavs.hw011.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String>, CommentRepositoryCustom {
    Flux<Comment> findAllByCommentedBookId(String id);

    Flux<Comment> findByTextContainingIgnoreCase(String partOfText);

    @Override
    Mono<Void> deleteById(@NonNull String id);
}
