package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.ingredient.Washable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WashServiceImpl implements WashService {

    private final DisplayProgressServiceImpl progressService;

    @Override
    public Washable washProduct(Washable product) {
        progressService.progress("Washing " + product.getClass().getSimpleName() + "...");
        product.wash();
        return product;
    }
}
