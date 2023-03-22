package ru.glavs.hw013.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw013.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
    Genre findByName(String genreName);
}
