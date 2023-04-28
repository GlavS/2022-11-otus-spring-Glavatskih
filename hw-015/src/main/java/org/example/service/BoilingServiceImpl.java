package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.ingredient.*;
import org.example.utensil.Pot;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoilingServiceImpl implements BoilingService{
    private final DisplayProgressServiceImpl progressService;

    @Override
    public Pot cookBeans(Beans beans) {

        Pot pot = new Pot();
        if (beans.isClean()) {
            pot.setBeans(beans);
        } else {
            throw new NotWashedException("Beans are not washed!");
        }
        pot.getBeans().setCooked(true);
        progressService.progress("Cooking beans...");
        return pot;
    }
    @Override
    public Pot cookBeanSoup(Pot pot, Roast roast) {
        if (!pot.getBeans().isCooked()) {
            throw new NotCookedException("Beans are not cooked!");
        }
        pot.setRoast(roast);
        progressService.progress("Adding roast and cooking soup...");
        return pot;
    }
    @Override
    public Pot cookCarrots(Pot pot, Carrots carrotsToBoil) {
        carrotsToBoil.setCooked(true);
        pot.setCarrots(carrotsToBoil);
        progressService.progress("Boiling coarse cut carrots...");
        return pot;
    }
}
