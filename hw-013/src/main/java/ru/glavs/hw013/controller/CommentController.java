package ru.glavs.hw013.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw013.controller.dto.CommentBookIdDto;
import ru.glavs.hw013.domain.Book;
import ru.glavs.hw013.domain.Comment;
import ru.glavs.hw013.service.CRUD.BookCRUD;
import ru.glavs.hw013.service.CRUD.CommentCRUD;

@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentCRUD commentCRUDService;
    private final BookCRUD bookCRUDService;

    @GetMapping("/comment/edit")
    public String editCommentPage(@RequestParam long id, @RequestParam long bookId, Model model) {
        Comment comment = commentCRUDService.findById(id);
        model.addAttribute("comment", comment);
        model.addAttribute("commentedBookId", bookId);
        return "/edit/edit-comment";
    }

    @PostMapping("/comment/edit")
    public String updateComment(CommentBookIdDto dto, RedirectAttributes attributes) {
        Comment comment = dto.toComment();
        long bookId = dto.getBookId();
        attributes.addAttribute("id", bookId);
        Book book = bookCRUDService.findById(bookId);
        comment.setCommentedBook(book);
        commentCRUDService.save(comment);
        attributes.addAttribute("id", comment.getCommentedBook().getId());
        return "redirect:/book/show";
    }

    @GetMapping("/comment/create")
    public String commentCreatePage(@RequestParam long id, Model model) {
        model.addAttribute("commentedBookId", id);
        return "/create/create-comment";
    }

    @PostMapping("/comment/create")
    public String createComment(CommentBookIdDto dto, RedirectAttributes attributes) {
        Comment comment = dto.toComment();
        long bookId = dto.getBookId();
        attributes.addAttribute("id", bookId);
        Book book = bookCRUDService.findById(bookId);
        comment.setCommentedBook(book);
        commentCRUDService.save(comment);
        return "redirect:/book/show";
    }

    @GetMapping("/comment/delete")
    public String deleteComment(@RequestParam long id, RedirectAttributes attributes) {
        Comment comment = commentCRUDService.findById(id);
        attributes.addAttribute("id", comment.getCommentedBook().getId());
        commentCRUDService.delete(comment);
        return "redirect:/book/show";
    }
}
