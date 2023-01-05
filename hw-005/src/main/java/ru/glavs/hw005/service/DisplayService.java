package ru.glavs.hw005.service;

import ru.glavs.hw005.domain.Book;

import java.util.List;

public interface DisplayService <T>{
    void displayList(List<T> list);
    void displayItem(int id);
    void displayAll();
}
