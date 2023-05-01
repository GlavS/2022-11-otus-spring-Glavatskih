package org.example.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Roast extends Ingredient {
    private final String name = "Roast";
    private Onions onions;
    private Carrots carrots;


    public Roast(Onions onions, Carrots carrots) {
        if(!onions.getCutType().equals(CutType.NONE)){
            this.onions = onions;
        } else {
            throw new NotCutException(onions.getName() +  " are not cut!");
        }

        if(!carrots.getCutType().equals(CutType.NONE)){
            this.carrots = carrots;
        } else {
            throw new NotCutException(carrots.getName() +  " are not cut!");
        }

        super.setCooked(false);
    }
}
