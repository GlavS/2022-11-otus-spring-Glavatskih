package ru.glavs.hw005.service.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.glavs.hw005.dao.BookDaoImpl;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class BookViewServiceTest {
    @Autowired
    AbstractViewService<Book> service;
    @Autowired
    EntityManager em;
    @Autowired
    BookDaoImpl dao;
    @Test
    @Transactional
    void displayItem() {
        Book book = dao.getById(1);
        List<Comment> bookComment = book.getComments();
        bookComment.remove(1);
        bookComment.get(0).setText("com");
        book.setComments(bookComment);
        service.printOne(book);
    }
    @Test
    @Transactional
    void displayAll(){
        List<Book> bookList = dao.getAll();
        service.printList(bookList);
    }
}