package ru.glavs.hw005.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw005.service.CRUD.BookCRUD;

import java.sql.SQLException;

@ShellComponent("Book commands")
@ShellCommandGroup("Book CRUD")
public class BookShellService {
    private final BookCRUD bookCrud;

    public BookShellService(BookCRUD bookCrud) {
        this.bookCrud = bookCrud;
    }

    @ShellMethod("List all books.")
    public void list() {
        bookCrud.readAll();
    }

    @ShellMethod("Show one book. Usage: show [id]")
    public void show(@ShellOption(help = "Usage: show [id]") int id) {
        //TODO: подумать, как.
        bookCrud.readBook(id);
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
    public void delete(@ShellOption int id) {
        bookCrud.delete(id);
    }

    @ShellMethod("Create new book.")
    public void create() {
        bookCrud.create();
    }

    @ShellMethod("Update book.")
    public void update() {
        bookCrud.update();
    }
}
