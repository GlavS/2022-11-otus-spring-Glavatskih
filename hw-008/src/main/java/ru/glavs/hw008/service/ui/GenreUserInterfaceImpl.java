package ru.glavs.hw008.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.GenreCRUD;
import ru.glavs.hw008.service.view.AbstractViewService;

import java.util.ArrayList;
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
    public List<Genre> requestGenres(String genreName) {
        List<Genre> genreList = new ArrayList<>();
        Genre genre = genreCRUDService.searchByGenre(genreName);
        if (genre == null) {
            String answer = ioService.readStringWithPrompt("No such genre in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                genreList.addAll(createGenres());
            } else {
                genreList.addAll(pickGenresFrom(genreCRUDService.findAll()));
            }
        }
        return genreList;
    }

    @Override
    public List<Genre> createGenres() {
        List<Genre> result = new ArrayList<>();
        String answer;
        do {
            Genre genre = new Genre();
            genre.setName(ioService.readStringWithPrompt("Please enter genre: "));
            result.add(genre);
            answer = ioService.readStringWithPrompt("Do you want to create more(y/n)?");
        }while(!answer.equalsIgnoreCase("n"));
        return result;
    }
    @Override
    public List<Genre> pickGenresFrom(List<Genre> genreList) {
        List<Genre> result = new ArrayList<>();
        genreList.sort(new GenreSort());
        String answer;
        do {
            displayService.printList(genreList);
            long genreId = ioService.readIntWithPrompt("Please enter desired genre id: ");
            result.add(genreCRUDService.findById(genreId));
            answer = ioService.readStringWithPrompt("Do you want to create more(y/n)?");
        }while(!answer.equalsIgnoreCase("n"));
        return result;
    }

    private static class GenreSort implements Comparator<Genre> {
        @Override
        public int compare(Genre o1, Genre o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
