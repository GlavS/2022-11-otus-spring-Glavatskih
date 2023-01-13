package ru.glavs.hw005.service.CRUD;

import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;

public interface AuthorRelationOperations {
    Author getAuthorBySurname(String surname);
    Author getAuthorForUpdate(Book bookForUpdate);
}
