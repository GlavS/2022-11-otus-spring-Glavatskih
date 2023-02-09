package ru.glavs.hw008.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, ObjectId> {
    List<Author> findBySurname(String surname);
}
