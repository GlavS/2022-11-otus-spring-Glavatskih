package ru.glavs.hw005.service.display;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.glavs.hw005.dao.AuthorDaoImpl;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorDisplayServiceTest {
    @Autowired
    private AuthorDisplayService service;
    @Autowired
    private AuthorDaoImpl authorDao;

    @Test
    void printOne() {
    }

    @Test
    void printList() {
        service.printList(authorDao.getAll());
    }

    @Test
    void displayItem() {
    }
}