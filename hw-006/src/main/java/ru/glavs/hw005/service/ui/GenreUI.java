package ru.glavs.hw005.service.ui;

import ru.glavs.hw005.domain.Genre;

import java.util.List;

public interface GenreUI {
    Genre requestGenre(String genreName);
    Genre createGenre();
    Genre pickGenreFrom(List<Genre> genreList);
}
