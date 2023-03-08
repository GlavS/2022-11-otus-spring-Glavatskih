package ru.glavs.hw008.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.service.CRUD.AuthorCRUDImpl;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorRestController.class)
@AutoConfigureDataMongo
@DisplayName("Контроллер Author REST Controller должен")
class AuthorRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorCRUDImpl authorCRUDService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("получать список всех авторов")
    void getAllAuthors() throws Exception {
        Author author1 = new Author("1", "Name", "Surname", "I.I.");
        Author author2 = new Author("2", "Name2", "Surname2", "U.U.");
        List<Author> authorList = List.of(author1, author2);

        String authorsAsJson = mapper.writeValueAsString(authorList);

        when(authorCRUDService.findAll()).thenReturn(authorList);
        mvc.perform(get("/api/authors")).andExpect(status().isOk()).andExpect(content().json(authorsAsJson));
        verify(authorCRUDService, times(1)).findAll();
    }

    @Test
    @DisplayName("создавать нового автора")
    void createAuthor() throws Exception {
        Author author = new Author(null, "Name", "Surname", "I.I.");
        Author authorSaved = new Author("1", "Name", "Surname", "I.I.");

        when(authorCRUDService.save(any())).thenReturn(authorSaved);

        String authorAsJson = mapper.writeValueAsString(author);
        String authorSavedAsJson = mapper.writeValueAsString(authorSaved);

        mvc.perform(post("/api/authors").contentType(MediaType.APPLICATION_JSON).content(authorAsJson)).andExpect(status().isOk()).andExpect(content().json(authorSavedAsJson));
        verify(authorCRUDService, times(1)).save(any(Author.class));
    }
}