package ru.glavs.hw007.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.glavs.hw007.domain.Book;
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


}
