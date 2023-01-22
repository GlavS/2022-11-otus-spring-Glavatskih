package ru.glavs.hw005.service.display;

import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.io.IOService;

public class CommentDisplayService extends AbstractDisplayService<Comment> implements DisplayService<Comment> {
    public CommentDisplayService(IOService ioService) {
        super(ioService);
    }

    @Override
    protected void displayItem(Comment item) {

    }

}
