package ru.glavs.hw016.service.ui;

import ru.glavs.hw016.domain.Genre;

import java.util.List;

public interface GenreUI {
    Genre requestGenre(String genreName);

    Genre createGenre();

    Genre pickGenreFrom(List<Genre> genreList);
}
