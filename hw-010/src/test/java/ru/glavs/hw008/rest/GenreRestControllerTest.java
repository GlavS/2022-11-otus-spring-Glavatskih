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
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.service.CRUD.GenreCRUDImpl;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GenreRestController.class)
@AutoConfigureDataMongo
@DisplayName("Контроллер Genre REST Controller должен")
class GenreRestControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    GenreCRUDImpl genreCRUDMock;


    @Test
    @DisplayName("возвращать список всех жанров")
    void shouldReturnListOfAllGenres() throws Exception {
        Genre genre1 = new Genre("1", "Genre1");
        Genre genre2 = new Genre("2",
                "Genre2");
        List<Genre> genreList = List.of(genre1, genre2);
        when(genreCRUDMock.findAll()).thenReturn(genreList);
        String result = mapper.writeValueAsString(genreList);

        mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

    @Test
    @DisplayName("сохранять новый жанр")
    void shouldCreateAndSaveNewGenre() throws Exception {
        Genre genre = new Genre(null, "Genre1");
        Genre savedGenre = new Genre("1", "Genre1");
        when(genreCRUDMock.save(genre)).thenReturn(savedGenre);
        String genreInRequest = mapper.writeValueAsString(genre);

        mvc.perform(post("/api/genres").contentType(MediaType.APPLICATION_JSON).content(genreInRequest))
                .andExpect(status().isOk());
        verify(genreCRUDMock, times(1)).save(any());
    }
}