package ru.glavs.hw008.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Author;
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
        List<Genre> result = new ArrayList<>();
        List<Genre> searchResultList = genreCRUDService.searchByGenre(genreName);
        if (searchResultList.size() == 0) {
            String answer = ioService.readStringWithPrompt("No such genre in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("н")) {
                result.addAll(createGenres());
            } else {
                result.addAll(pickGenresFrom(genreCRUDService.findAll()));
            }
        } else if (searchResultList.size() > 1) {
            result.addAll(pickGenresFrom(searchResultList));
        } else {
            result.add(searchResultList.get(0));
        }
        return result;
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
        }while(!answer.equalsIgnoreCase("n") && !answer.equalsIgnoreCase("т"));
        return result;
    }
    @Override
    public List<Genre> pickGenresFrom(List<Genre> genreList) {
        List<Genre> result = new ArrayList<>();
        genreList.sort(new GenreSort());
        String answer;
        do {
            displayService.printList(genreList);
            result.add(selectGenre());
            answer = ioService.readStringWithPrompt("Do you want to create more(y/n)?");
        }while(!answer.equalsIgnoreCase("n") && !answer.equalsIgnoreCase("т"));
        return result;
    }

    private Genre selectGenre() {
        List<Genre> result;
        String genreNamePart = ioService.readStringWithPrompt("Please enter genre (you may enter first few letters)");
        do {
            result = genreCRUDService.searchByGenre(genreNamePart);
            if (result.size() > 1) {
                genreNamePart = ioService.readStringWithPrompt("Please, enter few more letters of genre name: ");
            }
        }while (result.size()>1);
        displayService.printOne(result.get(0));
        ioService.println("Genre added");
        return result.get(0);
    }


    private static class GenreSort implements Comparator<Genre> {
        @Override
        public int compare(Genre o1, Genre o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
