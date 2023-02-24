package ru.glavs.hw008.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.service.CRUD.GenreCRUD;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenreRestController {

    private final GenreCRUD genreCRUDService;

    @GetMapping("/api/genre/all")
    public List<Genre> getAllGenres(){
        return genreCRUDService.findAll();
    }

}
