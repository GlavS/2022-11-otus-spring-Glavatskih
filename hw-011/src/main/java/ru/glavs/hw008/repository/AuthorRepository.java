package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.glavs.hw008.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Flux<Author> findBySurnameContainingIgnoreCase(String surnamePart);

    Flux<Author> findAllByIdIn(Collection<String> ids);
}
