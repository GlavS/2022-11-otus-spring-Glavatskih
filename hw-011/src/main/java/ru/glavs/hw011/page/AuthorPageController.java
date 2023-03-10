package ru.glavs.hw011.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorPageController {
    @GetMapping(value = "/author-edit", params = "bookId")
    public String showAuthorEditPage(@RequestParam String bookId, Model model){
        model.addAttribute("bookId", bookId);
        return "edit/author-edit";
    }

    @GetMapping("/author-edit")
    public String showAuthorCreatePage(){
        return "edit/author-edit";
    }

}
