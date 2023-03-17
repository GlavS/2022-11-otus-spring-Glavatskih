package ru.glavs.hw011.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.glavs.hw011.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
