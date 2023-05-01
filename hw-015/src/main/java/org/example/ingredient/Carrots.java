package org.example.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carrots extends Ingredient implements Washable, Cutable{
    private String name = "Carrots";
    private int weightInGrams;
    private boolean clean;
    private CutType cutType;


    public Carrots(int weightInGrams) {
        this.weightInGrams = weightInGrams;
        this.clean = false;
        super.setCooked(false);
        this.cutType = CutType.NONE;
    }

    @Override
    public void cut(CutType cutType) {
        this.cutType = cutType;
    }

    @Override
    public void wash() {
        this.clean = true;
    }
}
