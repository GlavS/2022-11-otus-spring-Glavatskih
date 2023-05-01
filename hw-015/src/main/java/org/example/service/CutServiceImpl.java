package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.ingredient.CutType;
import org.example.ingredient.Cutable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CutServiceImpl implements CutService {
    private final DisplayProgressServiceImpl progressService;
    @Override
    public Cutable cutProduct(Cutable product, CutType cutType){
        progressService.progress("Cutting " + product.getClass().getSimpleName() + " " + cutType.name() + "...");
        product.cut(cutType);
        return product;
    }
}
