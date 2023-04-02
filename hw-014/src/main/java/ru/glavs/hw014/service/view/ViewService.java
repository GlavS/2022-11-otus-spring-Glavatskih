package ru.glavs.hw014.service.view;

import java.util.List;

public interface ViewService<T> {
    void printOne(T item);

    void printList(List<T> itemList);
}
