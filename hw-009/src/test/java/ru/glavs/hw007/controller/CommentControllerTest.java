package ru.glavs.hw007.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw007.service.CRUD.BookCRUD;
import ru.glavs.hw007.service.CRUD.CommentCRUD;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CommentController.class)
@DisplayName("Класс CommentController должен")
class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentCRUD commentCRUDService;
    @MockBean
    private BookCRUD bookCRUDService;

    @Test
    void editCommentPage() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void commentCreatePage() {
    }

    @Test
    void createComment() {
    }

    @Test
    void deleteComment() {
    }
}