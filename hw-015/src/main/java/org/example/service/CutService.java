package org.example.service;

import org.example.ingredient.CutType;
import org.example.ingredient.Cutable;

public interface CutService {
    Cutable cutProduct(Cutable product, CutType cutType);
}
