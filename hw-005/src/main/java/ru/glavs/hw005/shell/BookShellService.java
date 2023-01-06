package ru.glavs.hw005.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.service.BookCRUDService;
import ru.glavs.hw005.service.DisplayService;

import java.sql.SQLException;

@ShellComponent("Book commands")
public class BookShellService implements BookShell {
    private final DisplayService<Book> bookDisplayService;
    private final BookCRUDService crud;

    public BookShellService(DisplayService<Book> bookDisplayService, BookCRUDService crud) {
        this.bookDisplayService = bookDisplayService;
        this.crud = crud;
    }

    @ShellMethod("List all books")
    public void list() {
        bookDisplayService.displayAll();
    }

    @ShellMethod("Show one book")
    public void show(@ShellOption(help = "Usage: show [id]") int id) {
        bookDisplayService.displayItem(id);
    }

    @ShellMethod("Show H2 console")
    public void console() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to H2 database: ", e);
        }
    }

    @ShellMethod("Delete book")
    public void delete() {
        crud.delete();
    }

}
