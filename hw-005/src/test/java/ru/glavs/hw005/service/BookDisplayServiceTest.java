package ru.glavs.hw005.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.glavs.hw005.dao.BookDaoImpl;
import ru.glavs.hw005.service.display.BookDisplayService;

@SpringBootTest
class BookDisplayServiceTest {

    @Autowired
    private BookDisplayService service;
    @Autowired
    private BookDaoImpl bookDao;


    @Test
    @DisplayName("NOT IMPLEMENTED!!") //TODO:!!!!
    void displayList() {
        //List<Book> bookList  = List.of(bookDao.getById(2), bookDao.getById(4));
    }

    @DisplayName("NOT IMPLEMENTED!!")
    @Test
    void displayItem() {
        //service.displayItem(4);
    }

    @DisplayName("NOT IMPLEMENTED!!")
    @Test
    void displayAll() {
       // service.displayAll();
    }
}