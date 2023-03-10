//package ru.glavs.hw008.rest;
//
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import ru.glavs.hw008.domain.Author;
//
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//public class AuthorRestController {
//
//    private final AuthorCRUD authorCRUDService;
//
//    @GetMapping("/api/authors")
//    public List<Author> getAllAuthors() {
//        return authorCRUDService.findAll();
//    }
//
//    @PostMapping("/api/authors")
//    public Author createAuthor(@RequestBody Author author) {
//        return authorCRUDService.save(author);
//    }
//}
