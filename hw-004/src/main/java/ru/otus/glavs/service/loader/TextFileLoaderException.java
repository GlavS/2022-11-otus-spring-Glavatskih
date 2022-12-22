package ru.otus.glavs.service.loader;

import java.io.IOException;

public class TextFileLoaderException extends RuntimeException {
    public TextFileLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
