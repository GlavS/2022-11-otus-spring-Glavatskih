package ru.glavs.hw005.service.display;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.glavs.hw005.dao.AuthorDaoImpl;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DisplayName("NOT IMPLEMENTED!!")
class AuthorDisplayServiceTest {
    @Autowired
    private AuthorDisplayService service;
    @Autowired
    private AuthorDaoImpl authorDao;

    @Test
    @DisplayName("NOT IMPLEMENTED!!") //TODO:!!!!
    void printOne() {
    }

    @Test
    @DisplayName("NOT IMPLEMENTED!!")
    void printList() {
        service.printList(authorDao.getAll());
    }

    @Test
    @DisplayName("NOT IMPLEMENTED!!")
    void displayItem() {
    }
}