package ru.glavs.hw011.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.glavs.hw011.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

}
