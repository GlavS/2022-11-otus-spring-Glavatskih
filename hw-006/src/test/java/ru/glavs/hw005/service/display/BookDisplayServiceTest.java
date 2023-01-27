package ru.glavs.hw005.service.display;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.BookDaoImpl;
import ru.glavs.hw005.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class BookDisplayServiceTest {
    @Autowired
    AbstractDisplayService<Book> service;
    @Autowired
    EntityManager em;
    @Autowired
    BookDaoImpl dao;
    @Test
    @Transactional
    void displayItem() {
        Book book = dao.getById(1);
        service.printOne(book);
    }
    @Test
    @Transactional
    void displayAll(){
        List<Book> bookList = dao.getAll();
        service.printList(bookList);
    }
}