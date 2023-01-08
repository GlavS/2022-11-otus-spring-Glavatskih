package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.BookDao;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.display.BookDisplayService;
import ru.glavs.hw005.service.display.DisplayService;

import java.util.List;

@Service
public class BookCRUDService implements BookCRUD {
    private final BookDao bookDao;
    private final AuthorCRUDService authorService;
    private final GenreCRUDService genreService;
    private final IOService ioService;
    private final DisplayService<Book> bookDisplayService;

    public BookCRUDService(BookDao bookDao,
                           AuthorCRUDService authorService,
                           GenreCRUDService genreService,
                           IOService ioService,
                           BookDisplayService bookDisplayService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
        this.ioService = ioService;
        this.bookDisplayService = bookDisplayService;
    }

    @Override
    public void delete(int id) {
        ioService.println("This book will be deleted:");
        Book book = bookDao.getById(id);
        bookDisplayService.printOne(book);
        if (yes()) {
            bookDao.delete(id);
            ioService.println("Book deleted: " + id);
        } else {
            ioService.println("Delete cancelled");
        }
    }

    @Override
    public Book create() {
        String surname = ioService.readStringWithPrompt("Please enter author's surname:");
        Author author = authorService.getAuthorBySurname(surname);
        if (author == null) {
            ioService.println("Book creation aborted");
            return null;
        }

        String genreName = ioService.readStringWithPrompt("Please enter desired genre:");
        Genre genre = genreService.getGenreByName(genreName);
        if (genre == null) {
            ioService.println("Book creation aborted");
            return null;
        }

        String title = ioService.readStringWithPrompt("Please enter title:");
        int id = bookDao.insertNew(author, genre, title);
        Book result = new Book(id, author, genre, title);
        bookDisplayService.printOne(result);
        ioService.println("New book written to DB");
        return result;
    }

    @Override
    public void readAll() {
        List<Book> bookList = bookDao.getAll();
        bookDisplayService.printList(bookList);
    }

    @Override
    public void readBook(int id) {
        Book book = bookDao.getById(id);
        bookDisplayService.printOne(book);
    }

    @Override
    public void update() {
        Book updatingBook = getUpdatingBook();
        Author author = authorService.getAuthorForUpdate(updatingBook);
        if (author == null) {
            return;
        }
        Genre genre = genreService.getUpdatingGenre(updatingBook);
        if (genre == null) {
            return;
        }
        String title = getUpdatingTitle(updatingBook);
        Book toUpdate = new Book(updatingBook.getId(), author, genre, title);
        bookDao.update(toUpdate);
        bookDisplayService.printOne(toUpdate);
        ioService.println("Book updated.");
    }

    private String getUpdatingTitle(Book updatingBook) {
        String title = ioService.readStringWithPrompt("Please enter new title, or ENTER to skip:");
        if (title.equals("")) {
            title = updatingBook.getTitle();
        }
        return title;
    }

    private boolean yes() {
        String answer = ioService.readStringWithPrompt("Are you sure (y/n)?");
        return answer.equalsIgnoreCase("y");
    }

    private Book getUpdatingBook() {
        bookDisplayService.printList(bookDao.getAll());
        int id = ioService.readIntWithPrompt("Please enter ID of the book you wish to edit:");
        Book result = bookDao.getById(id);
        bookDisplayService.printOne(result);
        ioService.println("This book will be updated.");
        return result;
    }
}
