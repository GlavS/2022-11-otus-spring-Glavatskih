package ru.glavs.hw013.controller;

import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw013.controller.dto.AuthorBookIdDto;
import ru.glavs.hw013.domain.Author;
import ru.glavs.hw013.service.CRUD.AuthorCRUD;

@Controller
@AllArgsConstructor
public class AuthorController {

    private final AuthorCRUD authorCRUDService;

    @GetMapping("/author")
    public String authorCreatePageOnBookUpdate(@RequestParam long id, Model model) {
        model.addAttribute("editedBookId", id);
        return "/edit/edit-author";
    }


    @PostMapping("/author/create-on-update")
    public String createAuthorOnBookUpdate(AuthorBookIdDto dto, RedirectAttributes attributes) {
        attributes.addAttribute("id", dto.getBookId());
        authorCRUDService.save(dto.toAuthor());
        return "redirect:/book/edit";
    }

    @GetMapping("author/new")
    public String newAuthorPage(){
        return "create/create-author";
    }

    @RequestMapping(value = "/author/create-new", method = {RequestMethod.GET, RequestMethod.POST})
    public String createNewAuthor(Author author){
        authorCRUDService.save(author);
        return "redirect:/book/create";
    }
}
