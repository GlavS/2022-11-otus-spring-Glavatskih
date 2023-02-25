package ru.glavs.hw008.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.service.CRUD.BookCRUD;
import ru.glavs.hw008.service.CRUD.BookCommentsCRUD;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookRestController {

    private final BookCommentsCRUD bookCommentsCRUDService;
    private final BookCRUD bookCRUDService;

    @GetMapping("/api/books")  //TODO: api path name?
    public List<BookWithComments> listAllBooks() {
        return bookCommentsCRUDService.readAll();
    }

    @GetMapping(value = "/api/books", params = "id")
    public Book getBookById(@RequestParam String id) {
        return bookCRUDService.getById(id);
    }


}
