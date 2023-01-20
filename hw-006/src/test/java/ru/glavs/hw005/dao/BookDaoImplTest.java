package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest
@DisplayName("Класс BookDao должен")
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    @Autowired
    private BookDaoImpl dao;

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void insertNew() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void count() {
    }
}