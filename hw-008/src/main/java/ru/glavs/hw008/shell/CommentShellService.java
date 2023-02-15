package ru.glavs.hw008.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.CommentCRUD;
import ru.glavs.hw008.service.ui.BookUI;
import ru.glavs.hw008.service.ui.CommentUI;
import ru.glavs.hw008.service.view.AbstractViewService;

import java.util.List;

@ShellComponent
@ShellCommandGroup("02. Comment CRUD")
@AllArgsConstructor
public class CommentShellService {

    private final AbstractViewService<BookWithComments> bookViewService;
    private final AbstractViewService<Comment> commentViewService;
    private final CommentUI commentUI;
    private final CommentCRUD commentCRUDService;
    private final BookUI bookUI;

    private final IOService ioService;

    @ShellMethod("Add comment to book.")
    void commentAdd() {
        String titlePart = ioService.readStringWithPrompt("Please enter title of book to show (you may type first few letters): ");
        BookWithComments foundBook = bookUI.pickByTitlePart(titlePart);


        Book bookToComment = BookWithComments.toBook(foundBook);
        bookViewService.printOne(foundBook);
        ioService.println("You are going to comment this book");
        String nickName = ioService.readStringWithPrompt("Please enter your nickname: ");
        commentUI.createCommentFor(bookToComment, nickName);
    }

    @ShellMethod("Delete existing book comment.")
    void commentDelete() {
        commentUI.deleteComment();
    }

    @ShellMethod("Show comments by book.")
    void commentsShow() {
        String titlePart = ioService.readStringWithPrompt("Please enter title of book to comment (you may type first few letters): ");
        BookWithComments foundBook = bookUI.pickByTitlePart(titlePart);

        List<Comment> commentList = commentCRUDService.findCommentsByBook(foundBook.getId());
        ioService.println("These are comments for book:");
        bookViewService.printOne(foundBook);
        ioService.println("Comments:");
        commentViewService.printList(commentList);
    }
}
