package ru.glavs.hw007.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw007.domain.Author;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Genre;
import ru.glavs.hw007.service.CRUD.AuthorCRUD;
import ru.glavs.hw007.service.CRUD.BookCRUD;
import ru.glavs.hw007.service.CRUD.GenreCRUD;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@DisplayName("Класс BookController должен")
class BookControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookCRUD bookCRUDService;
    @MockBean
    private AuthorCRUD authorCRUDService;
    @MockBean
    private GenreCRUD genreCRUDService;


    @Test
    @DisplayName("отображать список всех книг")
    void bookListPage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Book list</title>")));
        verify(bookCRUDService, times(1)).findAll();
    }

    @Test
    @DisplayName("отображать страницу просмотра одной книги")
    void showBookPage() throws Exception {
        when(bookCRUDService.findById(anyLong())).
                thenReturn(new Book(1L, new Author(), new Genre(), "TITLE", null));
        mvc.perform(get("/book/show")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>TITLE</title>")));
        verify(bookCRUDService, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("отображать страницу редактирования книги")
    void updateBookPage() throws Exception {
        when(bookCRUDService.findById(anyLong())).thenReturn(new Book());
        when(authorCRUDService.findAll()).thenReturn(new ArrayList<>());
        when(genreCRUDService.findAll()).thenReturn(new ArrayList<>());
        mvc.perform(get("/book/edit")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Edit book</title>")));
        verify(bookCRUDService, times(1)).findById(anyLong());
        verify(authorCRUDService, times(1)).findAll();
        verify(genreCRUDService, times(1)).findAll();
    }

    @Test
    @DisplayName("сохранять отредактированную книгу и делать редирект на страницу просмотра одной книги")
    void updateBook() throws Exception {
        when(bookCRUDService.findById(anyLong()))
                .thenReturn(new Book(1L, null, null, null, null));
        mvc.perform(post("/book/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/show?id=1"));
        verify(bookCRUDService, times(1)).findById(anyLong());
        verify(bookCRUDService, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("удалять книгу и делать редирект на страницу со списком всех книг")
    void deleteBook() throws Exception {
        mvc.perform(post("/book/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        verify(bookCRUDService, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("отображать страницу создания новой книги")
    void createBookPage() throws Exception {
        mvc.perform(get("/book/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Create book</title>")));
        verify(authorCRUDService, times(1)).findAll();
        verify(genreCRUDService, times(1)).findAll();
    }

    @Test
    @DisplayName("сохранять новую книгу и делать редирект на страницу со списком всех книг")
    void createNewBook() throws Exception {
        mvc.perform(post("/book/create-new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        verify(bookCRUDService, times(1)).save(any(Book.class));
    }
}