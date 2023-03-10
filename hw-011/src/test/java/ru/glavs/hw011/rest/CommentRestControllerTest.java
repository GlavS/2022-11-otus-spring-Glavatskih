//package ru.glavs.hw008.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.glavs.hw008.domain.Book;
//import ru.glavs.hw008.domain.Comment;
//import ru.glavs.hw008.rest.dto.CommentDto;
//import ru.glavs.hw008.service.CRUD.BookCRUD;
//import ru.glavs.hw008.service.CRUD.CommentCRUD;
//
//import java.util.Date;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CommentRestController.class)
//@AutoConfigureDataMongo
//@DisplayName("Контроллер Comment REST Controller должен")
//class CommentRestControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @MockBean
//    private CommentCRUD commentCRUDService;
//
//    @MockBean
//    private BookCRUD bookCRUDService;
//
//    private Comment comment;
//    private String commentAsJson;
//    private String commentDtoAsJson;
//
//    @BeforeEach
//    void init() throws Exception {
//        comment = new Comment("1", "Comment text", "Author", new Date(), new Book());
//        CommentDto dto = new CommentDto();
//        dto.setId("1");
//        dto.setText("Comment text");
//        dto.setAuthorNick("Author");
//        dto.setDate("23-07-2020");
//        dto.setCommentedBookId("2");
//
//        commentAsJson = mapper.writeValueAsString(comment);
//        commentDtoAsJson = mapper.writeValueAsString(dto);
//    }
//
//    @Test
//    @DisplayName("получать комментарий по его id")
//    void shouldGetCommentByItsId() throws Exception {
//        Comment comment = new Comment("1", "Comment text", "Author", new Date(), new Book());
//        when(commentCRUDService.findById("1")).thenReturn(comment);
//        String commentAsJson = mapper.writeValueAsString(comment);
//
//        mvc.perform(get("/api/comments").param("commentId", "1")).andExpect(status().isOk()).andExpect(content().json(commentAsJson));
//    }
//
//    @Test
//    @DisplayName("обновлять комментарий")
//    void shouldUpdateComment() throws Exception {
//        when(commentCRUDService.save(any())).thenReturn(comment);
//        mvc.perform(patch("/api/comments").contentType(MediaType.APPLICATION_JSON).content(commentDtoAsJson)).andExpect(status().isOk()).andExpect(content().json(commentAsJson));
//        verify(commentCRUDService, times(1)).save(any());
//        verify(bookCRUDService, times(1)).getById(any());
//    }
//
//    @Test
//    @DisplayName("создавать новый комментарий")
//    void shouldCreateComment() throws Exception {
//        when(commentCRUDService.save(any())).thenReturn(comment);
//        mvc.perform(post("/api/comments").contentType(MediaType.APPLICATION_JSON).content(commentDtoAsJson)).andExpect(status().isOk()).andExpect(content().json(commentAsJson));
//        verify(commentCRUDService, times(1)).save(any());
//        verify(bookCRUDService, times(1)).getById(any());
//    }
//
//    @Test
//    @DisplayName("удалять комментарий")
//    void shouldDeleteComment() throws Exception {
//        mvc.perform(delete("/api/comments").param("id", "1")).andExpect(status().isOk());
//        verify(commentCRUDService, times(1)).deleteById("1");
//    }
//}