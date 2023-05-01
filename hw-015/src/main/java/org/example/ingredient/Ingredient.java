package org.example.ingredient;

public abstract class Ingredient {
    private boolean cooked;

    public boolean isCooked() {
        return this.cooked;
    }

    public void setCooked(boolean isCooked) {
        this.cooked = isCooked;
    }
}
