package ru.glavs.hw007.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw007.service.CRUD.AuthorCRUDImpl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
@DisplayName("Класс AuthorController должен")
class AuthorControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AuthorCRUDImpl authorCRUDService;


    @Test
    @DisplayName("отображать страницу редактирования автора при обновлении книги")
    void shouldDisplayAuthorCreationPageOnBookUpdate() throws Exception {
        mvc.perform(get("/author?id={id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(" <title>Edit author</title>")));
    }

    @Test
    @DisplayName("сохранять автора при обновлении книги и перенаправлять на форму редактирования")
    void shouldSaveAuthorOnBookUpdateAndRedirectToBookEditView() throws Exception {
        mvc.perform(post("/author/create-on-update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/edit?id=0"));
        verify(authorCRUDService, times(1)).save(any());
    }

    @Test
    @DisplayName("отображать страницу создания автора")
    void shouldDisplayNewAuthorCreationPage() throws Exception {
        mvc.perform(get("/author/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Create author</title>")));
    }

    @Test
    @DisplayName("сохранять автора при создании книги и перенаправлять на форму создания")
    void shouldSaveAuthorOnCreationNewBookAndRedirectToBookCreationView() throws Exception {
        mvc.perform(post("/author/create-new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/create"));
        verify(authorCRUDService, times(1)).save(any());
    }
}