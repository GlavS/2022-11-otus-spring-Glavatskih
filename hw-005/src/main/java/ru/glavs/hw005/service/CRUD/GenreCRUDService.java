package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.GenreDao;

@Service
public class GenreCRUDService {
    private final GenreDao genreDao;

    public GenreCRUDService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

}
