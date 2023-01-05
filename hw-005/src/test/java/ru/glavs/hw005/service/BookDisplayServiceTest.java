package ru.glavs.hw005.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.dao.BookDao;
import ru.glavs.hw005.dao.BookDaoImpl;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.ioservice.StreamIOService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookDisplayServiceTest {

    @Autowired
    private BookDisplayService service;
    @Autowired
    private BookDaoImpl bookDao;


    @Test
    void displayList() {
        List<Book> bookList  = List.of(bookDao.getById(2), bookDao.getById(4));
    }

    @Test
    void displayItem() {
        service.displayItem(4);
    }

    @Test
    void displayAll() {
       // service.displayAll();
    }
}