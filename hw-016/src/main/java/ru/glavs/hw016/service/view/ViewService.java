package ru.glavs.hw016.service.view;

import java.util.List;

public interface ViewService<T> {
    void printOne(T item);

    void printList(List<T> itemList);
}
