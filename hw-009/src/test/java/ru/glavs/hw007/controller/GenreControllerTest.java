package ru.glavs.hw007.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw007.domain.Genre;
import ru.glavs.hw007.service.CRUD.GenreCRUDImpl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GenreController.class)
@DisplayName("Класс GenreController должен")
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreCRUDImpl genreCRUDService;

    @Test
    @DisplayName("отображать страницу редактирования жанра при обновлении книги")
    void shouldDisplayGenreEditPageOnBookUpdate() throws Exception {
        mvc.perform(get("/genre")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Edit genre</title>")));
    }

    @Test
    @DisplayName("сохранять новый жанр при обновлении книги и делать редирект на страницу обновления книги")
    void shouldSaveNewGenreOnBookUpdateAndPerformExpectedRedirect() throws Exception {
        mvc.perform(post("/genre/create-on-update")
                        .param("bookId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/edit?id=1"));
        verify(genreCRUDService, times(1)).save(any(Genre.class));
    }

    @Test
    @DisplayName("отображать страницу создания нового жанра при создании новой книги")
    void shouldDisplayNewGenreCreationPageOnNewBookCreation() throws Exception {
        mvc.perform(get("/genre/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Create genre</title>")));
    }

    @Test
    @DisplayName("сохранять новый жанр при создании новой книги и делать редирект на страницу создания книги")
    void shouldSaveNewGenreOnBookCreationAndPerformExpectedRedirect() throws Exception {
        mvc.perform(post("/genre/create-new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/create"));
        verify(genreCRUDService, times(1)).save(any(Genre.class));
    }
}