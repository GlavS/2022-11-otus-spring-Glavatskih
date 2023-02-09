package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.projections.BookWithComments;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {


    List<Book> findByTitleContaining(String titlePattern);

    List<Book> findAll();

    //Book findByIdWithComments(@Param("id") long id);
}
