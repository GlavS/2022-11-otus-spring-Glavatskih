package ru.glavs.hw008.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.rest.dto.BookDto;
import ru.glavs.hw008.service.CRUD.AuthorCRUD;
import ru.glavs.hw008.service.CRUD.BookCRUD;
import ru.glavs.hw008.service.CRUD.BookCommentsCRUD;
import ru.glavs.hw008.service.CRUD.GenreCRUD;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
@AutoConfigureDataMongo
@DisplayName("Контроллер Book REST Controller должен")
class BookRestControllerTest {

    @MockBean
    private BookCommentsCRUD bookCommentsCRUDService;
    @MockBean
    private BookCRUD bookCRUDService;
    @MockBean
    private AuthorCRUD authorCRUDService;
    @MockBean
    private GenreCRUD genreCRUDService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private List<BookWithComments> bookList;
    private BookWithComments book;
    private BookDto dto;

    @BeforeEach
    void init() {
        BookWithComments book1 = new BookWithComments("1",
                "Title1",
                List.of(new Author(), new Author()),
                List.of(new Genre(), new Genre()),
                List.of(new Comment(), new Comment())
        );
        BookWithComments book2 = new BookWithComments("2",
                "Title2",
                List.of(new Author(), new Author()),
                List.of(new Genre(), new Genre()),
                List.of(new Comment(), new Comment())
        );
        this.bookList = List.of(book1, book2);
        this.book = new BookWithComments("1",
                "Title1",
                List.of(new Author(), new Author()),
                List.of(new Genre(), new Genre()),
                List.of(new Comment(), new Comment())
        );
        this.dto = new BookDto();
        dto.setId("1");
        dto.setTitle("Title1");
        dto.setAuthorsIds(new String[]{"1", "2"});
        dto.setGenresIds(new String[]{"1", "2"});
    }

    @Test
    @DisplayName("возвращать список всех книг")
    void listAllBooks() throws Exception {
        when(bookCommentsCRUDService.readAll()).thenReturn(bookList);
        String expectedJson = mapper.writeValueAsString(bookList);

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(bookCommentsCRUDService, times(1)).readAll();
    }

    @Test
    @DisplayName("возвращать книгу по её id")
    void getBookById() throws Exception {
        when(bookCommentsCRUDService.readBookById("1")).thenReturn(book);
        String expectedJson = mapper.writeValueAsString(book);

        mvc.perform(get("/api/books").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(bookCommentsCRUDService, times(1)).readBookById("1");
    }

    @Test
    @DisplayName("обновлять книгу")
    void updateBook() throws Exception {
        String requestContent = mapper.writeValueAsString(dto);
        when(bookCRUDService.save(any(Book.class))).thenReturn(any(Book.class));

        mvc.perform(patch("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isOk());
        verify(bookCRUDService, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("создавать новую книгу")
    void createBook() throws Exception {
        String requestContent = mapper.writeValueAsString(dto);
        when(bookCRUDService.save(any(Book.class))).thenReturn(any(Book.class));

        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isOk());
        verify(bookCRUDService, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("удалять книгу")
    void deleteBook() throws Exception {
        mvc.perform(delete("/api/books").param("id", "1"))
                .andExpect(status().isOk());
    }
}