package ru.glavs.hw007.service.ui;

import ru.glavs.hw007.domain.Author;

import java.util.List;

public interface AuthorUI {
    Author requestAuthor(String surname);

    Author createAuthor();

    Author pickAuthorFrom(List<Author> authorList);
}
