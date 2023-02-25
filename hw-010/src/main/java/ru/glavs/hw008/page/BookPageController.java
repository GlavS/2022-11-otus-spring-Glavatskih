package ru.glavs.hw008.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookPageController {


    @GetMapping("/")
    public String listBooksPage() {
        return "list";
    }

    @GetMapping("/book-edit")
    public String editBookPage(@RequestParam String id, Model model){
        model.addAttribute("bookId", id);
        return "edit/book-edit";
    }
}
