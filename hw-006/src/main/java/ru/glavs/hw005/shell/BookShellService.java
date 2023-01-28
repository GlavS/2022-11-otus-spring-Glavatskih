package ru.glavs.hw005.shell;

import lombok.AllArgsConstructor;
import org.h2.tools.Console;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.CRUD.BookCRUD;
import ru.glavs.hw005.service.view.ViewService;
import ru.glavs.hw005.service.ui.BookUserInterface;

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
    private final BookUserInterface bookUI;

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

    @ShellMethod("Show one book. Usage: show [id]")
    public void show(@ShellOption(help = "Usage: show [id]") long id) {
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

    @ShellMethod("Delete book. Usage: delete [id]")
    public void delete(@ShellOption long id) {
        bookCrud.delete(id);
        ioService.printf("Book %d deleted.%n", id);
    }

    @ShellMethod("Create new book.")
    public void create() {
        Book bookToCreate = bookUI.create();
        bookCrud.save(bookToCreate);
        ioService.println("Book created");
    }

    @ShellMethod("Update book.")
    public void update() {
        long id = ioService.readIntWithPrompt("Please enter id of book to update: ");
        Book book = bookCrud.readBook(id);
        bookViewService.printOne(book);
        ioService.printf("This book will be updated");
        Book bookToUpdate = bookUI.update(book);
        bookCrud.save(bookToUpdate);
    }
}
