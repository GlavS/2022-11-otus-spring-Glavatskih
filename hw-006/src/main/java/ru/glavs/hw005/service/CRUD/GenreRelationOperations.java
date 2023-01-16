package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

public interface GenreRelationOperations {
    Genre getGenreByName(String genreName);
    Genre getGenreForUpdate(Book bookForUpdate);
}
