package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
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
    public List<Genre> searchByGenre(String genreNamePart) {
        return repository.findByNameContainingIgnoreCase(genreNamePart);
    }

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Genre> saveAll(List<Genre> genreList) {
        return repository.saveAll(genreList);
    }
}
