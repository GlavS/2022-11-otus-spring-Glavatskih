package ru.glavs.hw005.service.display;


import ru.glavs.hw005.io.IOService;

public abstract class AbstractTableFormat<T> {
    //    protected  String delimiter;
//    protected  String formatString;
//    protected  Object[] formatArgs;
    protected IOService ioService;

    public AbstractTableFormat(IOService ioService) {
        this.ioService = ioService;
    }

    protected abstract void displayItem(T item);

    protected void displayHeader(String delimiter, String formatString, Object[] formatArgs) {
        ioService.println(delimiter);
        ioService.printf(formatString, formatArgs);
        ioService.println(delimiter);
    }

    protected void displayFooter(String delimiter) {
        ioService.println(delimiter);
    }
}
