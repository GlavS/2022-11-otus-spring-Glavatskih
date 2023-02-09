package ru.glavs.hw008.service.ui;

import ru.glavs.hw008.domain.Author;

import java.util.List;

public interface AuthorUI {
    List<Author> requestAuthors(String surname);

    List<Author> createAuthors();

    List<Author> pickAuthorsFrom(List<Author> authorList);
}
