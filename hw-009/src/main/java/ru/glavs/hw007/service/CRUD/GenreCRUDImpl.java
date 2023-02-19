package ru.glavs.hw007.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw007.dao.GenreDao;
import ru.glavs.hw007.domain.Genre;

import java.util.List;

@Service
public class GenreCRUDImpl implements GenreCRUD {
    private final GenreDao dao;

    public GenreCRUDImpl(GenreDao dao) {
        this.dao = dao;
    }

    @Override
    public Genre searchByGenre(String genreName) {
        return dao.findByName(genreName);
    }

    @Override
    public List<Genre> findAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        return dao.save(genre);
    }

    @Override
    public Genre findById(long id) {
        return dao.getReferenceById(id);
    }
}
