package ru.glavs.hw013.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw013.domain.Genre;
import ru.glavs.hw013.security.config.SecurityConfig;
import ru.glavs.hw013.service.CRUD.GenreCRUDImpl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GenreController.class)
@Import(SecurityConfig.class) //https://github.com/spring-projects/spring-boot/issues/31162
@DisplayName("Класс GenreController должен")
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreCRUDImpl genreCRUDService;

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("отображать страницу редактирования жанра при обновлении книги")
    void shouldDisplayGenreEditPageOnBookUpdate() throws Exception {
        mvc.perform(get("/genre")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Edit genre</title>")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("сохранять новый жанр при обновлении книги и делать редирект на страницу обновления книги")
    void shouldSaveNewGenreOnBookUpdateAndPerformExpectedRedirect() throws Exception {
        mvc.perform(post("/genre/create-on-update")
                        .param("bookId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/edit?id=1"));
        verify(genreCRUDService, times(1)).save(any(Genre.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("отображать страницу создания нового жанра при создании новой книги")
    void shouldDisplayNewGenreCreationPageOnNewBookCreation() throws Exception {
        mvc.perform(get("/genre/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Create genre</title>")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("сохранять новый жанр при создании новой книги и делать редирект на страницу создания книги")
    void shouldSaveNewGenreOnBookCreationAndPerformExpectedRedirect() throws Exception {
        mvc.perform(post("/genre/create-new")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/create"));
        verify(genreCRUDService, times(1)).save(any(Genre.class));
    }

    @Test
    @DisplayName("запрещать переход по ссылке для незарегистрированных пользователей")
    void shouldDenyAccessForUnauthenticatedUsersToDisplayGenreEditPageOnBookUpdate() throws Exception {
        int unauthorized = 401;
        mvc.perform(get("/genre")
                        .param("id", "1"))
                .andExpect(status().is(unauthorized));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("запрещать отображение страницы создания нового жанра без роли 'ADMIN'")
    void shouldDenyAccessForUnauthorizedUsersToNewGenreCreationPageOnNewBookCreation() throws Exception {
        mvc.perform(get("/genre/new"))
                .andExpect(status().isForbidden());
    }
}