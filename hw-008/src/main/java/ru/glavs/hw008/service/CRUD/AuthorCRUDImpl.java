package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw008.dao.AuthorDao;
import ru.glavs.hw008.domain.Author;

import java.util.List;

@Service
public class AuthorCRUDImpl implements AuthorCRUD {
    private final AuthorDao dao;

    public AuthorCRUDImpl(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Author> searchBySurname(String surname) {
        return dao.findBySurname(surname);
    }

    @Override
    public List<Author> findAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public Author save(Author author) {
        return dao.save(author);
    }

    @Override
    public Author findById(long id) {
        return dao.getReferenceById(id);
    }
}