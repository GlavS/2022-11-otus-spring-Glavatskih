package ru.glavs.hw008.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.BookCRUD;
import ru.glavs.hw008.service.CRUD.BookCommentsCRUD;
import ru.glavs.hw008.service.ui.BookUI;
import ru.glavs.hw008.service.view.ViewService;

import java.util.List;

@ShellComponent
@ShellCommandGroup("01. Book CRUD")
@AllArgsConstructor
public class BookShellService {
    private final BookCommentsCRUD bookCommentsCRUD;
    private final BookCRUD bookCRUD;
    private final ViewService<BookWithComments> bookViewService;
    private final ViewService<Comment> commentViewService;
    private final IOService ioService;
    private final BookUI bookUI;

    @ShellMethod("List all books (with comments only - with \"c\" option).")
    public void list(@ShellOption(help = "Usage: list [c]", defaultValue = "") String withCommentsOnly) {
        if (withCommentsOnly.equals("c")) {
            List<BookWithComments> bookListWithComments = bookCommentsCRUD.readAllWithCommentsOnly();
            for (BookWithComments b : bookListWithComments) {
                bookViewService.printOne(b);
                commentViewService.printList(b.getComments());
            }
        } else {
            List<BookWithComments> bookList = bookCommentsCRUD.readAll();
            bookViewService.printList(bookList);
        }
    }

    @ShellMethod("Show one book.")
    public void show() {
        String titlePart = ioService.readStringWithPrompt("Please enter title of book to show (you may type first few letters): ");
        BookWithComments foundBook = bookUI.pickByTitlePart(titlePart);
        bookViewService.printOne(foundBook);
    }


    @ShellMethod("Delete book.")
    public void delete() {
        String titlePart = ioService.readStringWithPrompt("Please enter title of book to delete (you may type first few letters): ");
        BookWithComments foundBook = bookUI.pickByTitlePart(titlePart);
        bookViewService.printOne(foundBook);
        String answer = ioService.readStringWithPrompt("This book will be deleted. Are you sure(y/n)?");
        if(answer.equalsIgnoreCase("y")){
            Book bookToDelete = BookWithComments.toBook(foundBook);
            bookCRUD.deleteById(bookToDelete.getId());
            ioService.printf("Book \"%s\" deleted.%n", foundBook.getTitle());
        }
    }

    @ShellMethod("Create new book.")
    public void create() {
        Book bookToCreate = bookUI.create();
        bookCRUD.save(bookToCreate);
        ioService.println("Book created.");
    }

    @ShellMethod("Update book.")
    public void update() {
        String titlePart = ioService.readStringWithPrompt("Please enter title of book to update (you may type first few letters): ");
        BookWithComments foundBook = bookUI.pickByTitlePart(titlePart);
        bookViewService.printOne(foundBook);
        ioService.println("This book will be updated");
        Book bookToUpdate = BookWithComments.toBook(foundBook);
        Book updatedBook = bookUI.update(bookToUpdate);
        bookCRUD.save(updatedBook);
        ioService.printf("Book \"%s\" updated.%n", foundBook.getTitle());
    }
}
