package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.CRUD.GenreCRUD;
import ru.glavs.hw005.service.view.AbstractViewService;

import java.util.List;

@Service
public class GenreUserInterface {
    private final GenreCRUD genreCRUDService;
    private final IOService ioService;
    private final AbstractViewService<Genre> displayService;

    public GenreUserInterface(GenreCRUD genreCRUDService, IOService ioService, AbstractViewService<Genre> displayService) {
        this.genreCRUDService = genreCRUDService;
        this.ioService = ioService;
        this.displayService = displayService;
    }

    public Genre requestGenre(String genreName) {
        Genre genre = genreCRUDService.searchByGenre(genreName);
        if (genre.getId() == 0) {
            String answer = ioService.readStringWithPrompt("No such genre in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                genre = createGenre();
            } else {
                genre = pickGenreFrom(genreCRUDService.findAll());
            }
        }
        return genre;
    }


    public Genre createGenre() {
        Genre genre = new Genre();
        genre.setGenre(ioService.readStringWithPrompt("Please enter genre: "));
        return genre;
    }

    public Genre pickGenreFrom(List<Genre> genreList) {
        displayService.printList(genreList);
        long genreId = ioService.readIntWithPrompt("Please enter desired genre id: ");
        return genreCRUDService.findById(genreId);
    }

}
