package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.io.IOService;

@Service
public class AuthorDisplayService {
    private final IOService ioService;

    public AuthorDisplayService(IOService ioService) {
        this.ioService = ioService;
    }

}
