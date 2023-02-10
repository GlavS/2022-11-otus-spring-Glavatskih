package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.repository.AuthorRepository;

import java.util.List;
@Service
public class AuthorCRUDImpl implements AuthorCRUD {

    private final AuthorRepository repository;

    public AuthorCRUDImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> searchBySurname(String surnamePart) {
        return repository.findBySurnameContainingIgnoreCase(surnamePart);
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Author> saveAll(List<Author> authorList) {
        return repository.saveAll(authorList);
    }

    @Override
    public Author findById(long id) {
        return null;
    }
}
