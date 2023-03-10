package ru.glavs.hw011.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.glavs.hw011.domain.Genre;

import java.util.Collection;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    Flux<Genre> findByNameContainingIgnoreCase(String genreNamePart);

    Flux<Genre> findAllByIdIn(Collection<String> id);
}
