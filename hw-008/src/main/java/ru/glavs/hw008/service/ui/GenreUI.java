package ru.glavs.hw008.service.ui;

import ru.glavs.hw008.domain.Genre;

import java.util.List;

public interface GenreUI {
    List<Genre> requestGenres(String genreName);

    List<Genre> createGenres();

    List<Genre> pickGenresFrom(List<Genre> genreList);
}
