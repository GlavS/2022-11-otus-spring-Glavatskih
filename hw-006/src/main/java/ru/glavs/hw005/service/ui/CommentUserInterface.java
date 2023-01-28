package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.CommentDao;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.view.AbstractViewService;

@Service
public class CommentUserInterface {
    private final CommentDao dao;
    private final IOService ioService;
    private final AbstractViewService<Comment> displayService;

    public CommentUserInterface(CommentDao dao,
                                IOService ioService,
                                AbstractViewService<Comment> displayService) {
        this.dao = dao;
        this.ioService = ioService;
        this.displayService = displayService;
    }

    
}