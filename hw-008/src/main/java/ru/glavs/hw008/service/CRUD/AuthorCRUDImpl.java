package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Author;

import java.util.List;
@Service
public class AuthorCRUDImpl implements AuthorCRUD {
    @Override
    public List<Author> searchBySurname(String surname) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public Author findById(long id) {
        return null;
    }
}
