package ru.glavs.hw008.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.service.CRUD.BookCommentsCRUD;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookRestController {

    private final BookCommentsCRUD bookCommentsCRUDService;
    @GetMapping("/api/book/all")
    public List<BookWithComments> listAllBooks(){
        return bookCommentsCRUDService.readAll();
    }


//    readAll();

}
