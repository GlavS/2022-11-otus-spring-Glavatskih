package ru.glavs.hw011.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.glavs.hw011.domain.Author;

import java.util.Collection;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Flux<Author> findBySurnameContainingIgnoreCase(String surnamePart);

    Flux<Author> findAllByIdIn(Collection<String> ids);
}
