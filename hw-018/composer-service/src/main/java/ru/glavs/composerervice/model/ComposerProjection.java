package ru.glavs.composerervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ComposerProjection {
    private long id;
    private String familyName;
    private LocalDate birthDate;
    private LocalDate birthDateNewStyle;

}
