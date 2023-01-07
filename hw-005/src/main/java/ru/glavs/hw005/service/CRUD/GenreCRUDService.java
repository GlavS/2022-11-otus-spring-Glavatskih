package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.GenreDao;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.display.DisplayService;

import java.util.List;

@Service
public class GenreCRUDService {
    private final GenreDao genreDao;
    private final DisplayService<Genre> genreDisplayService;
    private final IOService ioService;

    public GenreCRUDService(GenreDao genreDao, DisplayService<Genre> genreDisplayService, IOService ioService) {
        this.genreDao = genreDao;
        this.genreDisplayService = genreDisplayService;
        this.ioService = ioService;
    }

    public void printList(List<Genre> genreList) {
        genreDisplayService.printList(genreList);
    }

    public List<Genre> searchByGenre(String genreName) {
        return genreDao.searchByGenre(genreName);
    }

    public void printAll() {
        genreDisplayService.printList(genreDao.getAll());
    }

    public Genre create() {
        String genreName = ioService.readStringWithPrompt("Please enter genre:");
        int id = genreDao.insertNew(genreName);
        Genre result = new Genre(id, genreName);
        genreDisplayService.printOne(result);
        ioService.println("New genre created");
        return result;
    }

    public Genre getById(int id) {
        return genreDao.getById(id);
    }
}
