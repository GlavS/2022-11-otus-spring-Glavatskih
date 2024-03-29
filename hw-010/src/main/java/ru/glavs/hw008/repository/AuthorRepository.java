package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findBySurnameContainingIgnoreCase(String surnamePart);

    List<Author> findAllByIdIn(Collection<String> ids);
}
