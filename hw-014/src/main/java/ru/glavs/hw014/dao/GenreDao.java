package ru.glavs.hw014.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw014.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
    Genre findByGenre(String genreName);
}
