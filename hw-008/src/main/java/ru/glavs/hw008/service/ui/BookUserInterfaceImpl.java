package ru.glavs.hw008.service.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.BookCommentsCRUD;
import ru.glavs.hw008.service.view.AbstractViewService;

import java.util.List;

@AllArgsConstructor
@Service
public class BookUserInterfaceImpl implements BookUI {
    private final IOService ioService;
    private final AuthorUI authorUI;
    private final GenreUI genreUI;
    private final BookCommentsCRUD bookWithCommentsCRUD;
    private final AbstractViewService<BookWithComments> bookDisplayService;


    @Override
    public Book create() {
        String title = ioService.readStringWithPrompt("Enter title: ");

        String surname = ioService.readStringWithPrompt("Please, enter author's surname: ");
        List<Author> authors = authorUI.requestAuthors(surname);

        String genreName = ioService.readStringWithPrompt("Please enter genre");
        List<Genre> genres = genreUI.requestGenres(genreName);

        return new Book(authors, genres, title);
    }

    @Override
    public Book update(Book book) {
        String title = ioService.readStringWithPrompt("Please enter new title, or enter to skip: ");
        if (title.equals("")) {
            title = book.getTitle();
        }

        String authorSurname = ioService.readStringWithPrompt("Please enter new author's surname, or enter to skip: ");
        List<Author> authors = authorUI.requestAuthors(authorSurname);

        String genreName = ioService.readStringWithPrompt("Please, enter genre or enter to skip: ");
        List<Genre> genres = genreUI.requestGenres(genreName);

        book.setTitle(title);
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

    @Override
    public BookWithComments pickByTitlePart(String titlePart) {
        List<BookWithComments> bookList = bookWithCommentsCRUD.readBookByTitlePart(titlePart);
        while (bookList.size() > 1) {
            bookDisplayService.printList(bookList);
            titlePart = ioService.readStringWithPrompt("Please, specify title more precicely");
            bookList = bookWithCommentsCRUD.readBookByTitlePart(titlePart);
        }
        return bookList.get(0);
    }
}
