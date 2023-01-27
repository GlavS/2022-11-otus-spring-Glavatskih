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
    private final AbstractDisplayService<Genre> genreDisplayService;

    public BookUserInterface(IOService ioService,
                             AuthorDao authorDao,
                             GenreDao genreDao,
                             AbstractDisplayService<Author> authorDisplayService,
                             AbstractDisplayService<Genre> genreDisplayService) {
        this.ioService = ioService;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.authorDisplayService = authorDisplayService;
        this.genreDisplayService = genreDisplayService;
    }

    public Book BIGSAVEMETHOD() {
        String title = ioService.readStringWithPrompt("Enter title: ");

        String surname = ioService.readStringWithPrompt("Please, enter author's surname: ");
        Author author = requestAuthor(surname);

        String genreName = ioService.readStringWithPrompt("Please enter genre");
        Genre genre = requestGenre(genreName);

        return new Book(author, genre, title);
    }

    public Book BIGUPDATEMETHOD(Book book) {
        String title = ioService.readStringWithPrompt("Please enter new title, or enter to skip: ");
        if (title.equals("")) {
            title = book.getTitle();
        }

        String authorSurname = ioService.readStringWithPrompt("Please enter new author's surname, or enter to skip: ");
        Author author = requestAuthor(authorSurname);

        String genreName = ioService.readStringWithPrompt("Please, enter genre or enter to skip: ");
        Genre genre = requestGenre(genreName);

        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }

    private Author requestAuthor(String surname) {
        Author author = new Author();
        List<Author> searchResultList = authorDao.searchBySurname(surname);
        if (searchResultList.size() == 0) {
            String answer = ioService.readStringWithPrompt("No such author in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                author = createAuthor();
            } else {
                author = pickAuthorFrom(authorDao.getAll());
            }

        } else if (searchResultList.size() > 1) {
            author = pickAuthorFrom(searchResultList);
        } else {
            author = searchResultList.get(0);
        }
        return author;
    }

    private Genre requestGenre(String genreName) {
        Genre genre = genreDao.searchByGenre(genreName);
        if (genre.getId() == 0) {
            String answer = ioService.readStringWithPrompt("No such genre in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                genre = createGenre();
            } else {
                genre = pickGenreFrom(genreDao.getAll());
            }
        }
        return genre;
    }

    private Author createAuthor() {
        Author author = new Author();
        author.setSurname(ioService.readStringWithPrompt("Enter author's surname: "));
        author.setName(ioService.readStringWithPrompt("Enter author's name: "));
        author.setInitials(ioService.readStringWithPrompt("Enter initials: "));
        return authorDao.save(author);
    }

    private Author pickAuthorFrom(List<Author> authorList) {
        authorDisplayService.printList(authorList);
        int authorId = ioService.readIntWithPrompt("Please enter desired author id: ");
        return authorDao.getById(authorId);
    }

    private Genre createGenre() {
        Genre genre = new Genre();
        genre.setGenre(ioService.readStringWithPrompt("Please enter genre: "));
        return genreDao.save(genre);
    }

    private Genre pickGenreFrom(List<Genre> genreList) {
        genreDisplayService.printList(genreList);
        int genreId = ioService.readIntWithPrompt("Please enter desired genre id: ");
        return genreDao.getById(genreId);
    }
}
