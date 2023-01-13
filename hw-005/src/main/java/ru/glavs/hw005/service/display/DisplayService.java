package ru.glavs.hw005.service.display;

import java.util.List;

public interface DisplayService<T> {
    void printOne(T item);

    void printList(List<T> itemList);
}
