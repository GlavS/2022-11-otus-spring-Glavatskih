package ru.glavs.hw011.rest.dto;

import lombok.Data;

@Data
public class BookDto {
    private String id;
    private String title;
    private String[] authorsIds;
    private String[] genresIds;

}
