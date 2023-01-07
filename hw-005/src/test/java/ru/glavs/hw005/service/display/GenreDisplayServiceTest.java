package ru.glavs.hw005.service.display;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.glavs.hw005.dao.BookDaoImpl;
import ru.glavs.hw005.dao.GenreDao;
import ru.glavs.hw005.dao.GenreDaoImpl;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DisplayName("NOT IMPLEMENTED!!")
class GenreDisplayServiceTest {
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private GenreDisplayService service;

    @Test
    @DisplayName("NOT IMPLEMENTED!!") //TODO:!!!!
    void displayList() {
        List<Genre> bookList  = List.of(genreDao.getById(1), genreDao.getById(2));
        service.printList(bookList);
    }

    @DisplayName("NOT IMPLEMENTED!!")
    @Test
    void displayItem() {
        //service.displayItem(4);
    }

    @DisplayName("NOT IMPLEMENTED!!")
    @Test
    void displayAll() {
        service.printList(genreDao.getAll());
    }
}