package ru.glavs.hw013.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw013.domain.Author;
import ru.glavs.hw013.service.CRUD.AuthorCRUDImpl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
@DisplayName("Класс AuthorController должен")
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorCRUDImpl authorCRUDService;

    private static final String TEST_ID = "1";


    @Test
    @WithMockUser
    @DisplayName("отображать страницу редактирования автора при обновлении книги")
    void shouldDisplayAuthorCreationPageOnBookUpdate() throws Exception {
        mvc.perform(get("/author")
                        .param("id", TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(" <title>Edit author</title>")));
    }

    @Test
    @WithMockUser
    @DisplayName("сохранять автора при обновлении книги и перенаправлять на форму редактирования")
    void shouldSaveAuthorOnBookUpdateAndRedirectToBookEditView() throws Exception {

        mvc.perform(post("/author/create-on-update")
                        .param("bookId", TEST_ID)
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/edit?id=" + TEST_ID));
        verify(authorCRUDService, times(1)).save(any(Author.class));
    }

    @Test
    @WithMockUser
    @DisplayName("отображать страницу создания автора")
    void shouldDisplayNewAuthorCreationPage() throws Exception {
        mvc.perform(get("/author/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Create author</title>")));
    }

    @Test
    @WithMockUser
    @DisplayName("сохранять автора при создании книги и перенаправлять на форму создания")
    void shouldSaveAuthorOnCreationNewBookAndRedirectToBookCreationView() throws Exception {
        mvc.perform(post("/author/create-new")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/create"));
        verify(authorCRUDService, times(1)).save(any(Author.class));
    }
    @Test
    @DisplayName("запрещать переход по ссылке для незарегистрированных пользователей")
    void shouldDenyAccessForUnauthorizedUsersToDisplayAuthorCreationPageOnBookUpdate() throws Exception {
        int unauthorized = 401;
        mvc.perform(get("/author")
                        .param("id", TEST_ID))
                .andExpect(status().is(unauthorized));
    }
}