package ru.glavs.hw005.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw005.service.CRUD.BookCRUDService;

import java.sql.SQLException;

@ShellComponent("Book commands")
public class BookShellService {

    private final BookCRUDService bookCrud;

    public BookShellService(BookCRUDService bookCrud) {
        this.bookCrud = bookCrud;
    }

    @ShellMethod("List all books")
    public void list() {
        bookCrud.readAll();
    }

    @ShellMethod("Show one book")
    public void show(@ShellOption(help = "Usage: show [id]") int id) {
        //TODO: подумать, как.
        bookCrud.readBook(id);
    }

    @ShellMethod("Show H2 console")
    public void console() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to H2 database: ", e);
        }
    }

    @ShellMethod("Delete book, usage: delete [id]")
    public void delete(@ShellOption int id) {
        bookCrud.delete(id);
    }
    @ShellMethod("Create new book")
    public void create(){
        bookCrud.create();
    }
}
