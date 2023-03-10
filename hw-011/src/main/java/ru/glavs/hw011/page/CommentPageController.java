package ru.glavs.hw011.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentPageController {

    @GetMapping("/comment-edit")
    public String commentEditPage(@RequestParam String commentId, @RequestParam String bookId, Model model){
        model.addAttribute("commentedBookId", bookId);
        model.addAttribute("commentId", commentId);
        return "edit/comment-edit";
    }
    @GetMapping("/comment-add")
    public String commentAddPage(@RequestParam String bookId, Model model){
        model.addAttribute("commentedBookId", bookId);
        return "edit/comment-edit";
    }
}
