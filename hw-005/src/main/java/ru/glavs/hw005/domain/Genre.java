package ru.glavs.hw005.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Genre {
    private final int id;
    private final String genre;
}
