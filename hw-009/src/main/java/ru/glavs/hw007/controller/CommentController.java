package ru.glavs.hw007.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.service.CRUD.CommentCRUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @PostMapping("/comment/edit")
    public String updateComment(
            @RequestParam long id,
            @RequestParam String text,
            @RequestParam String authorNick,
            @RequestParam String date,
            RedirectAttributes attributes
    ){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Comment comment = commentCRUDservice.findById(id);
        Date dateReceived;
        try {
            dateReceived = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse comment date: " + e.getMessage(), e);
        }
        comment.setText(text);
        comment.setAuthorNick(authorNick);
        comment.setDate(dateReceived);
        commentCRUDservice.save(comment);
        attributes.addAttribute("id", comment.getCommentedBook().getId());
        return "redirect:/edit";
    }
    @GetMapping("/comment/delete")
    public String deleteComment(@RequestParam long id, RedirectAttributes attributes){
        Comment comment = commentCRUDservice.findById(id);
        attributes.addAttribute("id",comment.getCommentedBook().getId());
        commentCRUDservice.delete(comment);
        return "redirect:/edit";
    }

}
