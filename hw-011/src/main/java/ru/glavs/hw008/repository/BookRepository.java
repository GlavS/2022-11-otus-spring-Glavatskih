package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.glavs.hw008.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {
    @Override
    Mono<Void> deleteById(String id);
}
