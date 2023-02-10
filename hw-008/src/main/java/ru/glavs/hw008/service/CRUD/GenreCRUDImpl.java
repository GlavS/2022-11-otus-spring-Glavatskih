package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.repository.GenreRepository;

import java.util.List;
@Service
public class GenreCRUDImpl implements GenreCRUD {
    private final GenreRepository repository;

    public GenreCRUDImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genre searchByGenre(String genreName) {
        return null;
    }

    @Override
    public List<Genre> findAll() {
        return null;
    }

    @Override
    public List<Genre> saveAll(List<Genre> genreList) {
        return repository.saveAll(genreList);
    }

    @Override
    public Genre findById(long id) {
        return null;
    }
}
