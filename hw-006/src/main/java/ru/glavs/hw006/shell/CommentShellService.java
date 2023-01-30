package ru.glavs.hw006.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.glavs.hw006.domain.Book;
import ru.glavs.hw006.io.IOService;
import ru.glavs.hw006.service.CRUD.BookCRUD;
import ru.glavs.hw006.service.ui.CommentUI;
import ru.glavs.hw006.service.view.AbstractViewService;

@ShellComponent
@ShellCommandGroup("02. Comment CRUD")
@AllArgsConstructor
public class CommentShellService {

    private final BookCRUD bookCRUDService;
    private final AbstractViewService<Book> bookViewService;
    private final CommentUI commentUI;

    private final IOService ioService;

    @ShellMethod("Add comment to book.")
    void commentAdd() {
        long id = ioService.readIntWithPrompt("Please enter id of book you want to comment: ");
        Book bookToComment = bookCRUDService.readBook(id);
        bookViewService.printOne(bookToComment);
        ioService.println("You are going to comment this book");
        commentUI.createCommentFor(bookToComment);
    }

    @ShellMethod("Delete existing book comment.")
    void commentDelete() {
        commentUI.deleteComment();
    }

}
