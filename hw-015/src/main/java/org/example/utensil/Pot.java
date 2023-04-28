package org.example.utensil;

import lombok.Getter;
import lombok.Setter;
import org.example.ingredient.Beans;
import org.example.ingredient.Carrots;
import org.example.ingredient.Roast;

@Getter
@Setter
public class Pot {
    private Roast roast;
    private Carrots carrots;
    private Beans beans;
}
