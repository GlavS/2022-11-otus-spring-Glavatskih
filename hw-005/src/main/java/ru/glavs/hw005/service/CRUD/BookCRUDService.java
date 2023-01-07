package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.BookDao;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.display.BookDisplayService;

import java.util.List;

@Service
public class BookCRUDService {
    private final BookDao bookDao;
    private final AuthorCRUDService authorService;
    private final GenreCRUDService genreService;
    private final IOService ioService;
    private final BookDisplayService bookDisplayService;

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
        bookDisplayService.displayBook(book);
        if (yes()) {
            bookDao.delete(id);
            ioService.println("Book deleted: " + id);
        } else {
            ioService.println("Delete cancelled");
        }
    }

    public void create() {
        String surname = ioService.readStringWithPrompt("Please enter author's surname:");
        List<Author> supposedAuthorList = authorService.searchBySurname(surname);
        if (supposedAuthorList.size() == 0) {
            String answer = ioService.readStringWithPrompt("No such author in database, do you want to create one (y/n)?");
            if(answer.equalsIgnoreCase("y")){
                authorService.create();
            } else {
                ioService.println("Author creation aborted");
            }
        } else {

        }

        //System.out.println(supposedAuthorList);

        //TODO:authorDAO::searchBySurname(surname)

        //TODO: create in CRUD

    }

    public void readAll() {
        List<Book> bookList = bookDao.getAll();
        bookDisplayService.displayAll(bookList);
    }

    public void readBook(int id) {
        Book book = bookDao.getById(id);
        bookDisplayService.displayBook(book);
    }

    public void update() {

        //TODO: update in CRUD
    }

    private boolean yes() {
        String answer = ioService.readStringWithPrompt("Are you sure (y/n)?");
        return answer.equalsIgnoreCase("y");
    }

}
