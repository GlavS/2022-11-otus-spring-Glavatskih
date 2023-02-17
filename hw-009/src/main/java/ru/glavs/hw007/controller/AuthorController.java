package ru.glavs.hw007.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    @GetMapping("/author")
    public String authorCreatePage(){
        return "edit-author"; // TODO: implement author create
    }

}
