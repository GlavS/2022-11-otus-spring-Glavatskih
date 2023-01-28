package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;

@Service
public class BookUserInterface {
    private final IOService ioService;
    private final AuthorUserInterface authorUI;
    private final GenreUserInterface genreUI;

    public BookUserInterface(IOService ioService,
                             AuthorUserInterface authorUI,
                             GenreUserInterface genreUI) {
        this.ioService = ioService;
        this.authorUI = authorUI;
        this.genreUI = genreUI;
    }

    public Book create() {
        String title = ioService.readStringWithPrompt("Enter title: ");

        String surname = ioService.readStringWithPrompt("Please, enter author's surname: ");
        Author author = authorUI.requestAuthor(surname);

        String genreName = ioService.readStringWithPrompt("Please enter genre");
        Genre genre = genreUI.requestGenre(genreName);

        return new Book(author, genre, title);
    }

    public Book update(Book book) {
        String title = ioService.readStringWithPrompt("Please enter new title, or enter to skip: ");
        if (title.equals("")) {
            title = book.getTitle();
        }

        String authorSurname = ioService.readStringWithPrompt("Please enter new author's surname, or enter to skip: ");
        Author author = authorUI.requestAuthor(authorSurname);

        String genreName = ioService.readStringWithPrompt("Please, enter genre or enter to skip: ");
        Genre genre = genreUI.requestGenre(genreName);

        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }
}
