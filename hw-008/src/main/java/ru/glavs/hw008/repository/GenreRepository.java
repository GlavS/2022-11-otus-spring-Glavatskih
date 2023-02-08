package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByName(String genreName);
}
