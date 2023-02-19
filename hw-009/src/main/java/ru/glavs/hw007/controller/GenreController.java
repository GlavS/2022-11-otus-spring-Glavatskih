package ru.glavs.hw007.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw007.domain.Genre;
import ru.glavs.hw007.service.CRUD.GenreCRUD;

@Controller
@AllArgsConstructor
public class GenreController {

    private GenreCRUD genreCRUDService;

    @GetMapping("/genre")
    public String genreCreatePage(@RequestParam long id, Model model) {
        model.addAttribute("editedBookId", id);
        return "edit-genre";
    }

    @PostMapping("/genre/create")
    public String createGenre(GenreBookIdDto dto, RedirectAttributes attributes) {
        attributes.addAttribute("id", dto.getBookId());
        genreCRUDService.save(dto.toGenre());
        return "redirect:/book/edit";
    }

    @Data
    private static class GenreBookIdDto {
        private String genre;
        private long bookId;

        private Genre toGenre() {
            return new Genre(genre);
        }
    }
}
