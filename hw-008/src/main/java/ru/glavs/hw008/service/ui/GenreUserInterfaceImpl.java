package ru.glavs.hw008.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.GenreCRUD;
import ru.glavs.hw008.service.view.AbstractViewService;

import java.util.Comparator;
import java.util.List;

@Service
public class GenreUserInterfaceImpl implements GenreUI {
    private final GenreCRUD genreCRUDService;
    private final IOService ioService;
    private final AbstractViewService<Genre> displayService;

    public GenreUserInterfaceImpl(GenreCRUD genreCRUDService, IOService ioService, AbstractViewService<Genre> displayService) {
        this.genreCRUDService = genreCRUDService;
        this.ioService = ioService;
        this.displayService = displayService;
    }

    @Override
    public Genre requestGenre(String genreName) {
        Genre genre = genreCRUDService.searchByGenre(genreName);
        if (genre == null) {
            String answer = ioService.readStringWithPrompt("No such genre in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                genre = createGenre();
            } else {
                genre = pickGenreFrom(genreCRUDService.findAll());
            }
        }
        return genre;
    }

    @Override
    public Genre createGenre() {
        Genre genre = new Genre();
        genre.setName(ioService.readStringWithPrompt("Please enter genre: "));
        return genre;
    }
    @Override
    public Genre pickGenreFrom(List<Genre> genreList) {
        //genreList.sort(new GenreSort());
        displayService.printList(genreList);
        long genreId = ioService.readIntWithPrompt("Please enter desired genre id: ");
        return genreCRUDService.findById(genreId);
    }


}
