package ru.glavs.hw007.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.service.CRUD.BookCRUD;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookCRUD bookCRUDservice;

    @GetMapping("/")
    public String bookListPage(Model model) {
        List<Book> bookList = bookCRUDservice.readAll();
        model.addAttribute("books", bookList);
        return "list";
    }

    @GetMapping("/book/show")
    public String showBookPage(@RequestParam long id, Model model) {
        Book book = bookCRUDservice.readBook(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/book/edit")
    public String updateBookPage(@RequestParam long id, Model model) {
        Book book = bookCRUDservice.readBook(id);
        model.addAttribute("book", book);
        return "edit-book";
    }
    @PostMapping("/book/edit")
    public String updateBook(Book bookUpdated, RedirectAttributes attributes){
        long id = bookUpdated.getId();
        Book book = bookCRUDservice.readBook(id);
        bookUpdated.setComments(book.getComments());
        attributes.addAttribute("id", book.getId());
        bookCRUDservice.save(bookUpdated);
        return "redirect:/book/show";
    }
}
