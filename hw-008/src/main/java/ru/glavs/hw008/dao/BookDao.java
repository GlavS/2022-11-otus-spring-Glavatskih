package ru.glavs.hw008.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.glavs.hw008.domain.Book;

import java.util.List;

public interface BookDao extends MongoRepository<Book, String> {


    List<Book> findByTitleContaining(String titlePattern);


    List<Book> getAllWithCommentsOnly();


    List<Book> findAll();

    Book findByIdWithComments(@Param("id") long id);
}
