package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.GenreDao;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

@Service
public class GenreCRUDImpl implements GenreCRUD {
    private final GenreDao dao;

    public GenreCRUDImpl(GenreDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public Genre searchByGenre(String genreName) {
        return dao.searchByGenre(genreName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return dao.getAll();
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        return dao.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findById(long id) {
        return dao.getById(id);
    }
}
