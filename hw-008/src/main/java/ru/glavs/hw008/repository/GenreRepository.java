package ru.glavs.hw008.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Genre;

public interface GenreDao extends MongoRepository<Genre, String> {
    Genre findByName(String genreName);
}
