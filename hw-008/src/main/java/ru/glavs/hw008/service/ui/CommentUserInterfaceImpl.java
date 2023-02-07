package ru.glavs.hw008.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.CommentCRUD;
import ru.glavs.hw008.service.view.AbstractViewService;

import java.util.Date;

@Service
public class CommentUserInterfaceImpl implements CommentUI {
    private final CommentCRUD commentCRUDService;
    private final IOService ioService;
    private final AbstractViewService<Comment> displayService;

    public CommentUserInterfaceImpl(CommentCRUD commentCRUDService,
                                    IOService ioService,
                                    AbstractViewService<Comment> displayService) {
        this.commentCRUDService = commentCRUDService;
        this.ioService = ioService;
        this.displayService = displayService;
    }

    @Override
    public void createCommentFor(Book book, String commentText, String nickName) {
        Comment comment = commentCRUDService.save(new Comment(commentText, nickName, new Date(), book));
        ioService.println("Comment added successfully");
        displayService.printOne(comment);
    }

    @Override
    public void deleteComment() {
        long commentId = ioService.readIntWithPrompt("Please enter comment id: ");
        Comment commentToDelete = commentCRUDService.findById(commentId);
        displayService.printOne(commentToDelete);
        String answer = ioService.readStringWithPrompt("This comment will be deleted. Are you sure(y/n)? ");
        if (answer.equalsIgnoreCase("y")) {
            commentCRUDService.delete(commentToDelete);
        } else {
            ioService.println("Delete cancelled");
        }
    }

    @Override
    public String prepareCommentText(){
        ioService.println("Please enter comment text (type END to finish): ");
        StringBuilder sb = new StringBuilder();
        String line;
        while (!(line = ioService.readStringNoPrompt()).equals("END")) {
            sb.append(line).append(" ");
        }
        return sb.toString();
    }

}
