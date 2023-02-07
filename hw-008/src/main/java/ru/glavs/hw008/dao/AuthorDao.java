package ru.glavs.hw008.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw008.domain.Author;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Long> {
    List<Author> findBySurname(String surname);
}
