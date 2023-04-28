package org.example.service;

import org.example.ingredient.Beans;
import org.example.ingredient.Carrots;
import org.example.ingredient.Roast;
import org.example.utensil.Pot;

public interface BoilingService {
    Pot cookBeans(Beans beans);
    Pot cookBeanSoup(Pot pot, Roast roast);
    Pot cookCarrots(Pot pot, Carrots carrotsToBoil);
}
