package ru.glavs.hw008.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.glavs.hw008.domain.Genre;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenreRestController {

    private final GenreCRUD genreCRUDService;

    @GetMapping("/api/genres")
    public List<Genre> getAllGenres() {
        return genreCRUDService.findAll();
    }

    @PostMapping("/api/genres")
    public Genre createGenre(@RequestBody Genre genre) {
        return genreCRUDService.save(genre);
    }
}
