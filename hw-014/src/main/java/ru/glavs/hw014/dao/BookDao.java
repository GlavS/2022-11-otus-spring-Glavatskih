package ru.glavs.hw014.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.glavs.hw014.domain.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-graph")
    List<Book> findByTitleContaining(String titlePattern);

    @Query("select b from Book b where b.comments is not empty ")
    @EntityGraph(value = "book-graph")
    List<Book> getAllWithCommentsOnly();


    @EntityGraph(value = "book-graph")
    List<Book> findAll();

    @Query("select b from Book b where b.id = :id and b.comments is not empty ")
    @EntityGraph(value = "book-graph")
    Book findByIdWithComments(@Param("id") long id);
}
