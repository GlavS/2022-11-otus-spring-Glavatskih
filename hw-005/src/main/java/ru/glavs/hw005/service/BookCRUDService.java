package ru.glavs.hw005.service;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.BookDao;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.ioservice.IOService;

@Service
public class BookCRUDService {
    private final BookDao bookDao;
    private final IOService ioService;

    public BookCRUDService(BookDao bookDao, IOService ioService) {
        this.bookDao = bookDao;
        this.ioService = ioService;
    }
    public void delete(){
        int id = ioService.readIntWithPrompt("Please enter book id:");
        //TODO: display book to delete, implement "are you sure"
        //Book book = bookDao.getById(id);
        bookDao.delete(id);
        ioService.println("Book deleted: " + id);
    }
    public void create(){
        //TODO: create in CRUD
    }
    public void update(){
        //TODO: update in CRUD
    }
}
