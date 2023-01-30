package ru.glavs.hw006.service.view;


import ru.glavs.hw006.io.IOService;

import java.util.List;

public abstract class AbstractViewService<T> implements ViewService<T> {
    protected String delimiter;
    protected String formatString;
    protected Object[] formatArgs;
    protected IOService ioService;

    public AbstractViewService(IOService ioService) {
        this.ioService = ioService;
    }

    protected abstract void displayItem(T item);

    protected void displayHeader() {
        ioService.println(delimiter);
        ioService.printf(formatString, formatArgs);
        ioService.println(delimiter);
    }

    protected void displayFooter() {
        ioService.println(delimiter);
    }

    @Override
    public void printOne(T item) {
        displayHeader();
        displayItem(item);
        displayFooter();
    }

    @Override
    public void printList(List<T> itemList) {
        displayHeader();
        itemList.forEach(this::displayItem);
        displayFooter();
    }
}
