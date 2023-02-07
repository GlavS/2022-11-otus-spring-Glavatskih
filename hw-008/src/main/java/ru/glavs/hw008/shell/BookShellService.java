package ru.glavs.hw008.shell;

import lombok.AllArgsConstructor;
import org.h2.tools.Console;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.BookCRUD;
import ru.glavs.hw008.service.ui.BookUI;
import ru.glavs.hw008.service.view.ViewService;

import java.sql.SQLException;
import java.util.List;

@ShellComponent
@ShellCommandGroup("01. Book CRUD")
@AllArgsConstructor
public class BookShellService {
    private final BookCRUD bookCrud;
    private final ViewService<Book> bookViewService;
    private final ViewService<Comment> commentViewService;
    private final IOService ioService;
    private final BookUI bookUI;

    @ShellMethod("List all books (with comments only - with \"c\" option).")
    public void list(@ShellOption(help = "Usage: list [c]", defaultValue = "") String withCommentsOnly) {
        if (withCommentsOnly.equals("c")) {
            List<Book> bookListWithComments = bookCrud.readAllWithCommentsOnly();
            for (Book b : bookListWithComments) {
                bookViewService.printOne(b);
                commentViewService.printList(b.getComments());
            }
        } else {
            List<Book> bookList = bookCrud.readAll();
            bookViewService.printList(bookList);
        }
    }

    @ShellMethod("Show one book.")
    public void show() {
        long id = ioService.readIntWithPrompt("Please enter id of book to show: ");
        Book bookToPrint = bookCrud.readBook(id);
        bookViewService.printOne(bookToPrint);
    }

    @ShellMethod("Show H2 console.")
    public void console() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to H2 database: ", e);
        }
    }

    @ShellMethod("Delete book.")
    public void delete() {
        long id = ioService.readIntWithPrompt("Please enter id of book to delete: ");
        bookCrud.deleteById(id);
        ioService.printf("Book %d deleted.%n", id);
    }

    @ShellMethod("Create new book.")
    public void create() {
        Book bookToCreate = bookUI.create();
        bookCrud.save(bookToCreate);
        ioService.println("Book created.");
    }

    @ShellMethod("Update book.")
    public void update() {
        long id = ioService.readIntWithPrompt("Please enter id of book to update: ");
        Book book = bookCrud.readBook(id);
        bookViewService.printOne(book);
        ioService.println("This book will be updated");
        Book bookToUpdate = bookUI.update(book);
        bookCrud.save(bookToUpdate);
        ioService.printf("Book %d updated.%n", id);
    }
}
