package ru.glavs.hw008.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GenrePageController {
    @GetMapping(value = "/genre-edit", params = "bookId")
    public String showGenreEditPage(@RequestParam String bookId, Model model){
        model.addAttribute("bookId", bookId);
        return "edit/genre-edit";
    }

    @GetMapping("/genre-edit")
    public String showGenreCreatePage(){
        return "edit/genre-edit";
    }
}
