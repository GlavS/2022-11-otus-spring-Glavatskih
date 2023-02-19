package ru.glavs.hw007.controller;

import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw007.domain.Author;
import ru.glavs.hw007.service.CRUD.AuthorCRUD;

@Controller
@AllArgsConstructor
public class AuthorController {

    AuthorCRUD authorCRUDService;

    @GetMapping("/author")
    public String authorCreatePage(@RequestParam long id, Model model) {
        model.addAttribute("editedBookId", id);
        return "edit-author";
    }


    @PostMapping("/author/create")
    public String createAuthor(AuthorBookIdDto dto, RedirectAttributes attributes) {
        attributes.addAttribute("id", dto.getBookId());
        authorCRUDService.save(dto.toAuthor());
        return "redirect:/book/edit";
    }


    @Data
    private static class AuthorBookIdDto {
        private String name;
        private String surname;
        private String initials;
        private  long bookId;
        private  Author toAuthor() {
            return new Author(name, surname, initials);
        }
    }
}
