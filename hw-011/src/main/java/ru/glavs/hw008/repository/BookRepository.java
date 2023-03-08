package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    @Override
    void deleteById(String id);
}
