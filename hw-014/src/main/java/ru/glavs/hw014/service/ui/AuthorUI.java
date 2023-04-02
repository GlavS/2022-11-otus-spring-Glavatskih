package ru.glavs.hw014.service.ui;

import ru.glavs.hw014.domain.Author;

import java.util.List;

public interface AuthorUI {
    Author requestAuthor(String surname);

    Author createAuthor();

    Author pickAuthorFrom(List<Author> authorList);
}
