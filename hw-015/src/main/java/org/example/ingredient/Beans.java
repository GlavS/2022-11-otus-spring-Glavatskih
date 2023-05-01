package org.example.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Beans extends Ingredient implements Washable{
    private final String name = "Beans";
    private int weightInGrams;
    private boolean clean;

    public Beans(int weightInGrams) {
        this.weightInGrams = weightInGrams;
        this.clean = false;
        super.setCooked(false);
    }

    @Override
    public void wash() {
        this.clean = true;
    }
}
