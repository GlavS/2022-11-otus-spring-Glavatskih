package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.glavs.hw008.domain.Genre;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    Flux<Genre> findByNameContainingIgnoreCase(String genreNamePart);

    Flux<Genre> findAllByIdIn(Collection<String> id);
}
