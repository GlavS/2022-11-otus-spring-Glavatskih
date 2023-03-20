package ru.glavs.hw011.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.glavs.hw011.domain.Comment;
import ru.glavs.hw011.repository.CommentRepository;
import ru.glavs.hw011.rest.dto.CommentDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("В роутере CommentRouter")
class CommentRouterTest {

    @Autowired
    @Qualifier("commentRoutes")
    private RouterFunction<ServerResponse> route;

    @Autowired
    private CommentRepository repository;
    private WebTestClient client;
    private Comment comment;


    @BeforeEach
    void init() {
        client = WebTestClient.bindToRouterFunction(route).build();
        List<Comment> commentList = repository.findAll().collectList().block();
        assert commentList != null;
        comment = commentList.get(0);
    }

    @Test
    @DisplayName("get-запрос эндпойнта 'comments{id}' должен вернуть комментарий по его id")
    void getApiCommentsByIdShouldReturnExpectedComment() {
        client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api/comments")
                                .queryParam("commentId", comment.getId())
                                .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Comment.class)
                .value(c -> assertThat(c).usingRecursiveComparison().isEqualTo(comment));
    }

    @Test
    @DisplayName("post-запрос эндпойнта 'comments' должен сохранить новый комментарий")
    void postApiCommentsShouldSaveNewComment() {
        CommentDto dto = new CommentDto();
        dto.setText("New comment");
        dto.setAuthorNick("Commentator");
        dto.setDate("1970-03-20T00:00:00.001Z");
        dto.setCommentedBook(comment.getCommentedBook());

        client.post()
                .uri("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("patch-запрос эндпойнта 'comments' должен сохранить комментарий")
    void patchApiCommentsShouldSaveComment() {
        CommentDto dto = new CommentDto();
        dto.setText("New comment");
        dto.setAuthorNick("Commentator");
        dto.setDate("1970-03-20T00:00:00.001Z");
        dto.setCommentedBook(comment.getCommentedBook());

        client.patch()
                .uri("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("delete-запрос эндпойнта 'comments' должен удалить комментарий")
    void deleteApiCommentsShouldDeleteComment() {
        Comment commentForDelete = new Comment("123", null, null, null, null);
        repository.save(commentForDelete).block();
        client.delete()
                .uri(uriBuilder -> uriBuilder.path("/api/comments/{id}").build("123"))
                .exchange()
                .expectStatus()
                .isOk();
        Comment deletedComment = repository.findById("123").block();
        assertThat(deletedComment).isNull();
    }
}