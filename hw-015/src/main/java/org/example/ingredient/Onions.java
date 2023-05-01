package org.example.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Onions extends Ingredient implements Cutable{
    private final String name = "Onions";
    private int weightInGrams;
    private CutType cutType;


    public Onions(int weightInGrams) {
        this.weightInGrams = weightInGrams;
        super.setCooked(false);
        this.cutType = CutType.NONE;
    }

    @Override
    public void cut(CutType cutType) {
        this.cutType = cutType;
    }
}
