package ru.glavs.hw007.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class GenreController {

    @GetMapping("/genre")
    public String genreCreatePage() {
        return "edit-genre"; //TODO: implement genre create
    }

}
