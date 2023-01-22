package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.AuthorDao;
import ru.glavs.hw005.dao.GenreDao;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.display.AbstractDisplayService;

import java.util.List;

@Service
public class BookUserInterface {
    private final IOService ioService;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final AbstractDisplayService<Author> authorDisplayService;

    public BookUserInterface(IOService ioService,
                             AuthorDao authorDao,
                             GenreDao genreDao,
                             AbstractDisplayService<Author> authorDisplayService) {
        this.ioService = ioService;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.authorDisplayService = authorDisplayService;
    }

    public Book BIGMETHOD() {
        String title = ioService.readStringWithPrompt("Enter title");
        String surname = ioService.readStringWithPrompt("Please, enter author's surname");
        Author author = new Author();
        List<Author> authorList = authorDao.searchBySurname(surname);
        if (authorList.size() == 0) {
            String answer = ioService.readStringWithPrompt("No such author in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("n")) return null;
            author.setSurname(ioService.readStringWithPrompt("Enter author's surname: "));
            author.setName(ioService.readStringWithPrompt("Enter author's name: "));
            author.setInitials(ioService.readStringWithPrompt("Enter initials: "));
            author = authorDao.save(author);
        } else if (authorList.size() > 1) {
            authorDisplayService.printList(authorList);
            int authorId = ioService.readIntWithPrompt("Enter id of desired author");
            author = authorDao.getById(authorId);
        } else {
            author = authorList.get(0);
        }

        String genreName = ioService.readStringWithPrompt("Please enter genre");
        Genre genre = genreDao.searchByGenre(genreName);
        if (genre.getId() == 0) {
            String answer = ioService.readStringWithPrompt("No such genre in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("n")) return null;
            genre.setGenre(ioService.readStringWithPrompt("Please enter genre: "));
            genre = genreDao.save(genre);
        }

        return new Book(0, author, genre, title, null);
    }

}
