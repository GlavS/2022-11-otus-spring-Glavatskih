package ru.glavs.hw008.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.BookCommentsCRUD;
import ru.glavs.hw008.service.ui.BookUI;
import ru.glavs.hw008.service.view.ViewService;

import java.util.List;

@ShellComponent
@ShellCommandGroup("01. Book CRUD")
@AllArgsConstructor
public class BookShellService {
    private final BookCommentsCRUD bookCrud;
    private final ViewService<BookWithComments> bookViewService;
    private final ViewService<Comment> commentViewService;
    private final IOService ioService;
    private final BookUI bookUI;

    @ShellMethod("List all books (with comments only - with \"c\" option).")
    public void list(@ShellOption(help = "Usage: list [c]", defaultValue = "") String withCommentsOnly) {
        if (withCommentsOnly.equals("c")) {
            List<BookWithComments> bookListWithComments = bookCrud.readAllWithCommentsOnly();
            for (BookWithComments b : bookListWithComments) {
                bookViewService.printOne(b);
                commentViewService.printList(b.getComments());
            }
        } else {
            List<BookWithComments> bookList = bookCrud.readAll();
            bookViewService.printList(bookList);
        }
    }

    @ShellMethod("Show one book.")
    public void show() {
        String titlePart = ioService.readStringWithPrompt("Please enter title of book to show (you may type first few letters): ");
        List<BookWithComments> bookList = bookCrud.readBookByTitlePart(titlePart);
        while (bookList.size() > 1){
            bookViewService.printList(bookList);
            titlePart = ioService.readStringWithPrompt("Please, specify title more precicely");
            bookList = bookCrud.readBookByTitlePart(titlePart);
        }
        bookViewService.printOne(bookList.get(0));
    }
//
//
//    @ShellMethod("Delete book.")
//    public void delete() {
//        long id = ioService.readIntWithPrompt("Please enter id of book to delete: ");
//        bookCrud.deleteById(id);
//        ioService.printf("Book %d deleted.%n", id);
//    }
//
//    @ShellMethod("Create new book.")
//    public void create() {
//        Book bookToCreate = bookUI.create();
//        bookCrud.save(bookToCreate);
//        ioService.println("Book created.");
//    }
//
//    @ShellMethod("Update book.")
//    public void update() {
//        long id = ioService.readIntWithPrompt("Please enter id of book to update: ");
//        Book book = bookCrud.readBook(id);
//        bookViewService.printOne(book);
//        ioService.println("This book will be updated");
//        Book bookToUpdate = bookUI.update(book);
//        bookCrud.save(bookToUpdate);
//        ioService.printf("Book %d updated.%n", id);
//    }
}
