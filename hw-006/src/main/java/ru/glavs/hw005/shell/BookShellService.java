package ru.glavs.hw005.shell;

import lombok.AllArgsConstructor;
import org.h2.tools.Console;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.CRUD.BookCRUD;
import ru.glavs.hw005.service.display.DisplayService;

import java.sql.SQLException;
import java.util.List;

@ShellComponent("Book commands")
@ShellCommandGroup("Book CRUD")
@AllArgsConstructor
public class BookShellService {
    private final BookCRUD bookCrud;
    private final DisplayService<Book> bookDisplayService;
    private final IOService ioService;

    @ShellMethod("List all books.")
    public void list() {
        List<Book> bookList = bookCrud.readAll();
        bookDisplayService.printList(bookList);
    }

    @ShellMethod("Show one book. Usage: show [id]")
    public void show(@ShellOption(help = "Usage: show [id]") int id) {
        bookDisplayService.printOne(bookCrud.readBook(id));
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
        ioService.printf("Book %d deleted.%n", id);
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
