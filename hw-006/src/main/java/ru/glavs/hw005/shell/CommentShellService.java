package ru.glavs.hw005.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.CRUD.BookCRUD;
import ru.glavs.hw005.service.ui.CommentUI;
import ru.glavs.hw005.service.view.AbstractViewService;

@ShellComponent
@ShellCommandGroup("02. Comment CRUD")
@AllArgsConstructor
public class CommentShellService {

    private final BookCRUD bookCRUDService;
    private final AbstractViewService<Book> bookViewService;
    private final CommentUI commentUI;

    private final IOService ioService;

    @ShellMethod("Add comment to book.")
    void commentAdd(@ShellOption(help = "Usage: comment-add [bookId]") long id) {
        Book bookToComment = bookCRUDService.readBook(id);
        bookViewService.printOne(bookToComment);
        ioService.println("You are going to comment this book");
        commentUI.create(bookToComment);

    }

    @ShellMethod("Delete existing book comment.")
    void commentDelete() {
        commentUI.delete();
    }

}
