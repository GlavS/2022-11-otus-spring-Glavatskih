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
public class BookCRUDService {
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

    public Book create() {
        String surname = ioService.readStringWithPrompt("Please enter author's surname:");
        List<Author> supposedAuthorList = authorService.searchBySurname(surname);
        Author author;
        if ((author = getAuthor(supposedAuthorList)) == null) {
            ioService.println("Book creation aborted");
            return null;
        }
        String genreName = ioService.readStringWithPrompt("Please enter desired genre:");
        List<Genre> supposedGenreList = genreService.searchByGenre(genreName);
        Genre genre;
        if ((genre = getGenre(supposedGenreList)) == null) {
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


    public void readAll() {
        List<Book> bookList = bookDao.getAll();
        bookDisplayService.printList(bookList);
    }

    public void readBook(int id) {
        Book book = bookDao.getById(id);
        bookDisplayService.printOne(book);
    }

    public void update() {
        Author author = null;
        Genre genre = null;
        String title = "";
        bookDisplayService.printList(bookDao.getAll());
        int id = ioService.readIntWithPrompt("Please enter ID of the book you wish to edit:");
        Book updatingBook = bookDao.getById(id);
        bookDisplayService.printOne(updatingBook);
        ioService.println("This book will be updated.");
        String surname = ioService.readStringWithPrompt("Please enter new author's surname, or ENTER to skip:");
        if (!surname.equals("")) {
            List<Author> supposedAuthorList = authorService.searchBySurname(surname);
            if ((author = getAuthor(supposedAuthorList)) == null) {
                ioService.println("Book creation aborted");
                return;
            }
        } else {
            author = updatingBook.getAuthor();
        }
        String genreName = ioService.readStringWithPrompt("Please enter new genre, or ENTER to skip:");
        if (!genreName.equals("")) {
            List<Genre> supposedGenreList = genreService.searchByGenre(genreName);
            if ((genre = getGenre(supposedGenreList)) == null) {
                ioService.println("Book creation aborted");
                return;
            }
        } else {
            genre = updatingBook.getGenre();
        }
        title = ioService.readStringWithPrompt("Please enter new title, or ENTER to skip:");
        if (title.equals("")) {
            title = updatingBook.getTitle();
        }
        Book toUpdate = new Book(id, author, genre, title);
        bookDao.update(toUpdate);
        bookDisplayService.printOne(toUpdate);
        ioService.println("Book updated.");
    }

    private boolean yes() {
        String answer = ioService.readStringWithPrompt("Are you sure (y/n)?");
        return answer.equalsIgnoreCase("y");
    }

    private Author getAuthor(List<Author> authorList) {
        if (authorList.size() == 0) {
            String answer = ioService.readStringWithPrompt("No such author in database, do you want to create one (y/n)?");
            if (answer.equalsIgnoreCase("y")) {
                return authorService.create();
            } else {
                ioService.println("Author creation aborted");
                return null;
            }
        } else {
            authorService.printList(authorList);
            int id = ioService.readIntWithPrompt("Please indicate author's ID:");
            return authorService.getById(id);
        }
    }

    private Genre getGenre(List<Genre> genreList) {
        if (genreList.size() == 0) {
            genreService.printAll();
            String answer = ioService.readStringWithPrompt("""
                    You entered non-existing genre. You may want:
                    1. Create new genre, enter 'c'
                    2. Pick one from existing list, enter 'p'
                    3. Cancel book creation, enter 'quit'
                    """);
            if (answer.equalsIgnoreCase("c") || answer.equalsIgnoreCase("с")) {
                return genreService.create();
            } else if (answer.equalsIgnoreCase("p") || answer.equalsIgnoreCase("з")) {
                int id = ioService.readIntWithPrompt("Please indicate genre's ID:");
                return genreService.getById(id);
            } else {
                ioService.println("Genre creation aborted");
                return null;
            }
        } else {
            genreService.printList(genreList);
            int id = ioService.readIntWithPrompt("Please indicate genre's ID:");
            return genreService.getById(id);
        }
    }
}
