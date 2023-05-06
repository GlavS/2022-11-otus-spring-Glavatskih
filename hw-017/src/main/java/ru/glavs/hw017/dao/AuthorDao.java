package ru.glavs.hw017.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw017.domain.Author;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Long> {
    List<Author> findBySurname(String surname);
}
