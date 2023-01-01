package ru.glavs.hw005.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
    private final int id;
    private final Author author;
    private final Genre genre;
    private final String name;
}
