package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.ingredient.Washable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WashService {

    private final DisplayProgressService progressService;

    public Washable washProduct(Washable product) {
        progressService.progress("Washing " + product.getClass().getSimpleName() + "...");
        product.wash();
        return product;
    }
}
