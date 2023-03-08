package ru.glavs.hw008.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Genre;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findByNameContainingIgnoreCase(String genreNamePart);

    List<Genre> findAllByIdIn(Collection<String> id);
}
