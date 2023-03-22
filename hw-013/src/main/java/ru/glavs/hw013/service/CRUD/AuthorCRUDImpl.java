package ru.glavs.hw013.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw013.dao.AuthorDao;
import ru.glavs.hw013.domain.Author;

import java.util.List;

@Service
public class AuthorCRUDImpl implements AuthorCRUD {
    private final AuthorDao dao;

    public AuthorCRUDImpl(AuthorDao dao) {
        this.dao = dao;
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
