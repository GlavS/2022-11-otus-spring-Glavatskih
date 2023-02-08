//package ru.glavs.hw008.shell;
//
//import lombok.AllArgsConstructor;
//import org.springframework.shell.standard.ShellCommandGroup;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import ru.glavs.hw008.domain.Book;
//import ru.glavs.hw008.domain.Comment;
//import ru.glavs.hw008.io.IOService;
//import ru.glavs.hw008.service.CRUD.BookCRUD;
//import ru.glavs.hw008.service.CRUD.CommentCRUD;
//import ru.glavs.hw008.service.ui.CommentUI;
//import ru.glavs.hw008.service.view.AbstractViewService;
//
//import java.util.List;
//
//@ShellComponent
//@ShellCommandGroup("02. Comment CRUD")
//@AllArgsConstructor
//public class CommentShellService {
//
//    private final BookCRUD bookCRUDService;
//    private final AbstractViewService<Book> bookViewService;
//    private final AbstractViewService<Comment> commentViewService;
//    private final CommentUI commentUI;
//    private final CommentCRUD commentCRUDService;
//
//    private final IOService ioService;
//
//    @ShellMethod("Add comment to book.")
//    void commentAdd() {
//        long id = ioService.readIntWithPrompt("Please enter id of book you want to comment: ");
//        Book bookToComment = bookCRUDService.readBook(id);
//        bookViewService.printOne(bookToComment);
//        ioService.println("You are going to comment this book");
//        String nickName = ioService.readStringWithPrompt("Please enter your nickname: ");
//        String commentText = commentUI.prepareCommentText();
//        commentUI.createCommentFor(bookToComment, commentText, nickName);
//    }
//
//    @ShellMethod("Delete existing book comment.")
//    void commentDelete() {
//        commentUI.deleteComment();
//    }
//
//    @ShellMethod("Show comments by book.")
//    void commentsShow(){
//        long bookId = ioService.readIntWithPrompt("Please enter book id: ");
//        List<Comment> commentList = commentCRUDService.findCommentsByBook(bookId);
//        ioService.println("These are comments for book:");
//        bookViewService.printOne(bookCRUDService.readBook(bookId));
//        ioService.println("Comments:");
//        commentViewService.printList(commentList);
//    }
//
//}
