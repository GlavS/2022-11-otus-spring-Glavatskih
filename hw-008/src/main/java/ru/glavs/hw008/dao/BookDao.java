package ru.glavs.hw008.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.glavs.hw008.domain.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {


    List<Book> findByTitleContaining(String titlePattern);


    List<Book> getAllWithCommentsOnly();


    List<Book> findAll();

    Book findByIdWithComments(@Param("id") long id);
}
