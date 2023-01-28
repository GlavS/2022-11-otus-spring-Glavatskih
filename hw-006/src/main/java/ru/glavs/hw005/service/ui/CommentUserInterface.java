package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.CommentDao;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.CRUD.CommentCRUD;
import ru.glavs.hw005.service.view.AbstractViewService;

import java.util.Date;

@Service
public class CommentUserInterface {
    private final CommentCRUD commentCRUDService;
    private final IOService ioService;
    private final AbstractViewService<Comment> displayService;

    public CommentUserInterface(CommentCRUD commentCRUDService,
                                IOService ioService,
                                AbstractViewService<Comment> displayService) {
        this.commentCRUDService = commentCRUDService;
        this.ioService = ioService;
        this.displayService = displayService;
    }

//TODO: implement UI with comments

    public void create(Book book){
        String nickName = ioService.readStringWithPrompt("Please enter your nickname");
        ioService.println("Please enter comment text (type END to finish): ");
        StringBuilder sb = new StringBuilder();
        String line;
        while(!(line = ioService.readStringNoPrompt()).equals("END")){
            sb.append(line).append(" ");
        }
        Comment comment = commentCRUDService.save(new Comment(sb.toString(), nickName, new Date(), book));
        ioService.println("Comment added successfully");
        displayService.printOne(comment);
    }

}
