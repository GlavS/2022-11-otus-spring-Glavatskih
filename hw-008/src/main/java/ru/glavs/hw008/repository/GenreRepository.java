package ru.glavs.hw008.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, ObjectId> {
    List<Genre> findByNameContainingIgnoreCase(String genreNamePart);
}
