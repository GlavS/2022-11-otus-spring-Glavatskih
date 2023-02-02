package ru.glavs.hw007.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.glavs.hw007.domain.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-graph")
    List<Book> findByTitleContaining(String titlePattern);

    @Query("select b from Book b where b.comments is not empty ")
    @EntityGraph(value = "Book-graph")
    List<Book> getAllWithCommentsOnly();


    @EntityGraph(value = "book-graph")
    List<Book> findAll();
}
