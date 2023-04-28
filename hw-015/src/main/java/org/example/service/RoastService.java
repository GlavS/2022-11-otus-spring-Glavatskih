package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.ingredient.Roast;
import org.example.utensil.FryingPan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoastService {
    private final DisplayProgressService progressService;
    public FryingPan cookRoast(Roast roast){
        progressService.progress("Frying carrots-onion roast...");
        FryingPan fryingPan = new FryingPan();
        fryingPan.setRoast(roast);
        fryingPan.getRoast().setCooked(true);
        return fryingPan;
    }
}
