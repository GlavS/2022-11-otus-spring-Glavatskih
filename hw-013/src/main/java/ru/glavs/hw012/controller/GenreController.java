package ru.glavs.hw012.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.glavs.hw012.domain.Genre;
import ru.glavs.hw012.service.CRUD.GenreCRUD;

@Controller
@AllArgsConstructor
public class GenreController {

    private final GenreCRUD genreCRUDService;

    @GetMapping("/genre")
    public String genreCreatePageOnBookUpdate(@RequestParam long id, Model model) {
        model.addAttribute("editedBookId", id);
        return "/edit/edit-genre";
    }

    @PostMapping("/genre/create-on-update")
    public String createGenreOnBookUpdate(GenreBookIdDto dto, RedirectAttributes attributes) {
        attributes.addAttribute("id", dto.getBookId());
        genreCRUDService.save(dto.toGenre());
        return "redirect:/book/edit";
    }

    @GetMapping("/genre/new")
    public String newGenrePage(){
        return "create/create-genre";
    }

    @RequestMapping(value = "/genre/create-new", method = {RequestMethod.GET, RequestMethod.POST})
    public String createNewGenre(Genre genre){
        genreCRUDService.save(genre);
        return "redirect:/book/create";
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
