//package ru.glavs.hw008.rest;
//
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import ru.glavs.hw008.domain.Book;
//import ru.glavs.hw008.domain.projections.BookWithComments;
//import ru.glavs.hw008.rest.dto.BookDto;
//
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//public class BookRestController {
//
//
//    @GetMapping("/api/books")
//    public List<BookWithComments> listAllBooks() {
//        return bookCommentsCRUDService.readAll();
//    }
//
//    @GetMapping(value = "/api/books", params = "id")
//    public BookWithComments getBookById(@RequestParam String id) {
//        return bookCommentsCRUDService.readBookById(id);
//    }
//
//    @PatchMapping("/api/books")
//    public Book updateBook(@RequestBody BookDto dto){
//       return bookCRUDService.save(new Book(
//                dto.getId(),
//                authorCRUDService.findAllByIdArray(dto.getAuthorsIds()),
//                genreCRUDService.findAllByIdArray(dto.getGenresIds()),
//                dto.getTitle()
//        ));
//    }
//    @PostMapping("/api/books")
//    public Book createBook(@RequestBody BookDto dto){
//        return bookCRUDService.save(new Book(
//                authorCRUDService.findAllByIdArray(dto.getAuthorsIds()),
//                genreCRUDService.findAllByIdArray(dto.getGenresIds()),
//                dto.getTitle()
//        ));
//    }
//    @DeleteMapping("/api/books")
//    public void deleteBook(@RequestParam String id){
//        bookCRUDService.deleteById(id);
//    }
//}
