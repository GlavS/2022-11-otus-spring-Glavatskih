package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.io.IOService;

@Service
public class BookUserInterface {
    private final IOService ioService;

    public BookUserInterface(IOService ioService) {
        this.ioService = ioService;
    }
}
