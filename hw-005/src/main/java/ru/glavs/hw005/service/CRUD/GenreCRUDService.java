package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.GenreDao;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.display.DisplayService;

import java.util.List;

@Service
public class GenreCRUDService implements GenreCRUD, GenreRelationOperations {
    private final GenreDao genreDao;
    private final DisplayService<Genre> genreDisplayService;
    private final IOService ioService;

    public GenreCRUDService(GenreDao genreDao, DisplayService<Genre> genreDisplayService, IOService ioService) {
        this.genreDao = genreDao;
        this.genreDisplayService = genreDisplayService;
        this.ioService = ioService;
    }

    @Override
    public void printList(List<Genre> genreList) {
        genreDisplayService.printList(genreList);
    }
    @Override
    public List<Genre> searchByGenre(String genreName) {
        return genreDao.searchByGenre(genreName);
    }
    @Override
    public void printAll() {
        genreDisplayService.printList(genreDao.getAll());
    }
    @Override
    public Genre create() {
        String genreName = ioService.readStringWithPrompt("Please enter genre:");
        int id = genreDao.insertNew(genreName);
        Genre result = new Genre(id, genreName);
        genreDisplayService.printOne(result);
        ioService.println("New genre created");
        return result;
    }
    @Override
    public Genre getById(int id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre getGenreByName(String genreName) {
        List<Genre> supposedGenreList = searchByGenre(genreName);
        if (supposedGenreList.size() == 0) {
            printAll();
            String answer = ioService.readStringWithPrompt(
                    "You entered non-existing genre. You may want:" + System.lineSeparator() +
                            "1. Create new genre, enter 'c'" + System.lineSeparator() +
                            "2. Pick one from existing list, enter 'p'" + System.lineSeparator() +
                            "3. Cancel book creation, enter 'quit'"
            );
            if (answer.equalsIgnoreCase("c") || answer.equalsIgnoreCase("с")) {
                return create();
            } else if (answer.equalsIgnoreCase("p") || answer.equalsIgnoreCase("з")) {
                int id = ioService.readIntWithPrompt("Please indicate genre's ID:");
                return getById(id);
            } else {
                ioService.println("Genre creation aborted");
                return null;
            }
        } else {
            printList(supposedGenreList);
            int id = ioService.readIntWithPrompt("Please indicate genre's ID:");
            return getById(id);
        }
    }

    @Override
    public Genre getGenreForUpdate(Book bookForUpdate) {
        Genre result;
        String genreName = ioService.readStringWithPrompt("Please enter new genre, or ENTER to skip:");
        if (!genreName.equals("")) {
            if ((result = getGenreByName(genreName)) == null) {
                ioService.println("Genre creation aborted");
                return null;
            }
        } else {
            result = bookForUpdate.getGenre();
        }
        return result;
    }
}
