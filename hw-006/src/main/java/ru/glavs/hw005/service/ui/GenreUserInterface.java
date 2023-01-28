package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.GenreDao;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.display.AbstractDisplayService;

import java.util.List;

@Service
public class GenreUserInterface {
    private final GenreDao dao;
    private final IOService ioService;
    private final AbstractDisplayService<Genre> displayService;

    public GenreUserInterface(GenreDao dao, IOService ioService, AbstractDisplayService<Genre> displayService) {
        this.dao = dao;
        this.ioService = ioService;
        this.displayService = displayService;
    }

    public Genre requestGenre(String genreName) {
        Genre genre = dao.searchByGenre(genreName);
        if (genre.getId() == 0) {
            String answer = ioService.readStringWithPrompt("No such genre in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                genre = createGenre();
            } else {
                genre = pickGenreFrom(dao.getAll());
            }
        }
        return genre;
    }


    public Genre createGenre() {
        Genre genre = new Genre();
        genre.setGenre(ioService.readStringWithPrompt("Please enter genre: "));
        return dao.save(genre);
    }

    public Genre pickGenreFrom(List<Genre> genreList) {
        displayService.printList(genreList);
        long genreId = ioService.readIntWithPrompt("Please enter desired genre id: ");
        return dao.getById(genreId);
    }

}
