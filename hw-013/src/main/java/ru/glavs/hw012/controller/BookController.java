package ru.glavs.hw012.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw012.domain.Author;
import ru.glavs.hw012.domain.Book;
import ru.glavs.hw012.domain.Genre;
import ru.glavs.hw012.service.CRUD.AuthorCRUD;
import ru.glavs.hw012.service.CRUD.BookCRUD;
import ru.glavs.hw012.service.CRUD.GenreCRUD;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookCRUD bookCRUDService;
    private final AuthorCRUD authorCRUDService;
    private final GenreCRUD genreCRUDService;

    @GetMapping("/")
    public String bookListPage(Model model, Authentication authentication) {
        List<Book> bookList = bookCRUDService.findAll();
        model.addAttribute("books", bookList);
        model.addAttribute("username", authentication.getName());
        return "list";
    }

    @GetMapping("/book/show")
    public String showBookPage(@RequestParam long id, Model model) {
        Book book = bookCRUDService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/book/edit")
    public String updateBookPage(@RequestParam long id, Model model) {
        Book book = bookCRUDService.findById(id);
        List<Author> authorList = authorCRUDService.findAll();
        List<Genre> genreList = genreCRUDService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorList);
        model.addAttribute("genres", genreList);
        return "/edit/edit-book";
    }

    @PostMapping("/book/edit")
    public String updateBook(Book bookUpdated, RedirectAttributes attributes) {
        long id = bookUpdated.getId();
        Book book = bookCRUDService.findById(id);
        bookUpdated.setComments(book.getComments());
        attributes.addAttribute("id", book.getId());
        bookCRUDService.save(bookUpdated);
        return "redirect:/book/show";
    }

    @RequestMapping(value = "/book/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteBook(@RequestParam long id){
        bookCRUDService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/book/create")
    public String createBookPage(Model model){
        List<Author> authorList = authorCRUDService.findAll();
        List<Genre> genreList = genreCRUDService.findAll();
        model.addAttribute("authors", authorList);
        model.addAttribute("genres", genreList);
        return "create/create-book";
    }

    @RequestMapping(value = "/book/create-new", method = {RequestMethod.POST, RequestMethod.GET})
    public String createNewBook(Book book){
        bookCRUDService.save(book);
        return "redirect:/";
    }

}
