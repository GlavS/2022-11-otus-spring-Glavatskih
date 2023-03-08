package ru.glavs.hw008.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.service.CRUD.AuthorCRUD;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorRestController {

    private final AuthorCRUD authorCRUDService;
    @GetMapping("/api/authors")
    public List<Author> getAllAuthors(){
        return authorCRUDService.findAll();
    }

    @PostMapping("/api/authors")
    public Author createAuthor(@RequestBody Author author){
        return authorCRUDService.save(author);
    }
}
