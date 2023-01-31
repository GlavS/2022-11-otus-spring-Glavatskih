package ru.glavs.hw005.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {
    private final int id;
    private final String name;
    private final String surname;
    private final String initials;
}
