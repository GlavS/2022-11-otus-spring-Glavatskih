package ru.glavs.hw007.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.service.CRUD.BookCRUD;
import ru.glavs.hw007.service.CRUD.CommentCRUD;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @DisplayName("отображать страницу редактирования комментария")
    void shouldDisplayCommentEditingPage() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "1");
        params.add("bookId", "2");

        when(commentCRUDService.findById(anyLong())).thenReturn(new Comment());
        mvc.perform(get("/comment/edit")
                        .params(params))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Edit comment</title>")));
        verify(commentCRUDService, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("сохранять отредактированный комментарий и делать редирект на страницу показа одной книги")
    void shouldSaveUpdatedCommentAndPerformExpectedRedirect() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("bookId", "1");
        params.add("id", "1");
        params.add("text", "text");
        params.add("authorNick", "authorNick");
        params.add("date", "2001-01-01");

        when(bookCRUDService.findById(anyLong())).thenReturn(new Book(1L, null, null, null, null));
        mvc.perform(post("/comment/edit")
                        .params(params))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/show?id=1"));
        verify(bookCRUDService, times(1)).findById(anyLong());
        verify(commentCRUDService, times(1)).save(any(Comment.class));
    }

    @Test
    @DisplayName("отображать страницу создания нового комментария")
    void shouldDisplayCommentCreationPage() throws Exception {
        mvc.perform(get("/comment/create")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Create comment</title>")));
    }

    @Test
    @DisplayName("сохранять новый комментарий и делать редирект на страницу показа одной книги")
    void shouldSaveNewCommentAndPerformExpectedRedirect() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("bookId", "1");
        params.add("id", "1");
        params.add("text", "text");
        params.add("authorNick", "authorNick");
        params.add("date", "2001-01-01");

        when(bookCRUDService.findById(anyLong())).thenReturn(new Book(1L, null, null, null, null));
        mvc.perform(post("/comment/create")
                        .params(params))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/show?id=1"));
        verify(bookCRUDService, times(1)).findById(anyLong());
        verify(commentCRUDService, times(1)).save(any(Comment.class));
    }

    @Test
    @DisplayName("удалять комментарий и делать редирект на страницу показа одной книги")
    void shouldDeleteCommentAndPerformExpectedRedirect() throws Exception {
        when(commentCRUDService.findById(anyLong()))
                .thenReturn(new Comment(null, null, null,
                        new Book(1L, null, null, null, null)));
        mvc.perform(get("/comment/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/show?id=1"));
        verify(commentCRUDService, times(1)).findById(anyLong());
        verify(commentCRUDService, times(1)).delete(any(Comment.class));

    }
}