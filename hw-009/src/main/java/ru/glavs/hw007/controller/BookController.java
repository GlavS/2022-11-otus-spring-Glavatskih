package ru.glavs.hw007.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.glavs.hw007.domain.Book;
import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.service.CRUD.BookCRUD;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookCRUD bookCRUDservice;

    @GetMapping("/")
    public String bookListPage(Model model){
        List<Book> bookList = bookCRUDservice.readAll();
        model.addAttribute("books", bookList);
        return "list";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam long id, Model model){
        Book book = bookCRUDservice.readBook(id);
        model.addAttribute("book", book);
        return "book";
    }
}
