package ru.glavs.hw008.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Author;

import java.util.List;

public interface AuthorDao extends MongoRepository<Author, String> {
    List<Author> findBySurname(String surname);
}
