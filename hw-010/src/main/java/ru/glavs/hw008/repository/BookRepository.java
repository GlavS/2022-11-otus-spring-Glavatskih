package ru.glavs.hw008.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Book;

public interface BookRepository extends MongoRepository<Book, ObjectId>, BookRepositoryCustom {
}
