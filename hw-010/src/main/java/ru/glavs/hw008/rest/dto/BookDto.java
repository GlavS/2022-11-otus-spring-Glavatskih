package ru.glavs.hw008.rest.dto;

import lombok.Data;

@Data
public class BookDto {
    private String id;
    private String title;
    private String[] authorsIds;
    private String[] genresIds;

}
