package ru.glavs.hw008.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.io.IOService;

@Service
public class BookUserInterfaceImpl implements BookUI {
    private final IOService ioService;
    private final AuthorUI authorUI;
    private final GenreUI genreUI;

    public BookUserInterfaceImpl(IOService ioService,
                                 AuthorUI authorUI,
                                 GenreUI genreUI) {
        this.ioService = ioService;
        this.authorUI = authorUI;
        this.genreUI = genreUI;
    }
    @Override
    public Book create() {
//        String title = ioService.readStringWithPrompt("Enter title: ");
//
//        String surname = ioService.readStringWithPrompt("Please, enter author's surname: ");
//        Author author = authorUI.requestAuthor(surname);
//
//        String genreName = ioService.readStringWithPrompt("Please enter genre");
//        Genre genre = genreUI.requestGenre(genreName);
//
//        return new Book(author, genre, title);
        return null;
    }
    @Override
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
//        book.setAuthor(author);
//        book.setGenre(genre);
        return book;
    }
}
