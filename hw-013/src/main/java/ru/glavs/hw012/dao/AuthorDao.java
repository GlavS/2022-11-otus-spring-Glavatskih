package ru.glavs.hw012.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw012.domain.Author;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Long> {
    List<Author> findBySurname(String surname);
}
