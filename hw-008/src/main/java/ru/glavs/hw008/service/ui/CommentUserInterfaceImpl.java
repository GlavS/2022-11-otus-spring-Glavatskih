package ru.glavs.hw008.service.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.CommentCRUD;
import ru.glavs.hw008.service.view.AbstractViewService;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentUserInterfaceImpl implements CommentUI {
    private final CommentCRUD commentCRUDService;
    private final BookUI bookUI;
    private final IOService ioService;
    private final AbstractViewService<Comment> displayService;


    @Override
    public void createCommentFor(Book book, String commentText, String nickName) {
        Comment comment = commentCRUDService.save(new Comment(commentText, nickName, new Date(), book));
        ioService.println("Comment added successfully");
        displayService.printOne(comment);
    }

    @Override
    public void deleteComment() {
        String titlePart = ioService.readStringWithPrompt("Please enter title of book to comment (you may type first few letters): ");
        BookWithComments foundBook = bookUI.pickByTitlePart(titlePart);
        List<Comment> commentList = commentCRUDService.findCommentsByBook(foundBook.getId());
        if(commentList == null){
            ioService.println("There are no comments for this book");
            return;
        }
        if(commentList.size() > 1){
            do{
                displayService.printList(commentList);
                String partOfText = ioService.readStringWithPrompt("Please enter part of comment text of comment you want to delete: ");
                commentList = commentCRUDService.findByCommentText(partOfText);
                if(commentList.size() == 1){
                    break;
                } else {
                    ioService.println("Please, enter bigger part of comment text: ");
                }
            }while(commentList.size() > 1);
        }
        Comment commentToDelete = commentList.get(0);
        displayService.printOne(commentToDelete);
        String answer = ioService.readStringWithPrompt("This comment will be deleted. Are you sure(y/n)? ");
        if (answer.equalsIgnoreCase("y")) {
            commentCRUDService.delete(commentToDelete);
            ioService.println("Delete successful");
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
