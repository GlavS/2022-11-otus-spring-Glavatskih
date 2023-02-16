package ru.glavs.hw007.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.service.CRUD.CommentCRUD;

@Controller
@AllArgsConstructor
public class CommentController {

    private CommentCRUD commentCRUDservice;

    @GetMapping("/comment/edit")
    public String editCommentPage(@RequestParam long id, Model model){
        Comment comment = commentCRUDservice.findById(id);
        model.addAttribute("comment", comment);
        return "edit-comment";
    }
}
