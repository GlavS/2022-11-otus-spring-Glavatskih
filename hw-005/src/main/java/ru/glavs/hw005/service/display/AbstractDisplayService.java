package ru.glavs.hw005.service.display;


import ru.glavs.hw005.io.IOService;

import java.util.List;

public abstract class AbstractDisplayService<T> implements DisplayService<T> {
    protected String delimiter;
    protected String formatString;
    protected Object[] formatArgs;
    protected IOService ioService;

    public AbstractDisplayService(IOService ioService) {
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
