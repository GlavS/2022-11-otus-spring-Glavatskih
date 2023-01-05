package ru.glavs.hw005.ioservice;

public interface IOService {
    void print(String message);

    void println(String message);

    void printf(String message, Object... args);
    int readIntWithPrompt(String prompt);
}
