package ru.glavs.composerervice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "composers")
@NoArgsConstructor
@Getter
@Setter
public class Composer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "family")
    private String familyName;
    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Override
    public String toString() {
        return "Composer{" +
                "familyName='" + familyName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
