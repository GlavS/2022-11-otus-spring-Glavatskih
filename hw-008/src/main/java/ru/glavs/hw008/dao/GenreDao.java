package ru.glavs.hw008.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw008.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
    Genre findByGenre(String genreName);
}
