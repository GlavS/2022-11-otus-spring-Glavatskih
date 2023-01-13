package ru.glavs.hw005.io;

public interface IOService {
    void println(String message);

    void printf(String message, Object... args);

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);
}
