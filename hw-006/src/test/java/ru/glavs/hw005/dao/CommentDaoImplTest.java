package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(CommentDaoImpl.class)
@DisplayName("Класс CommentDaoImpl должен")
class CommentDaoImplTest {

    @Autowired
    private CommentDaoImpl dao;
    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void getCommentsForBook() {
    }
}