package ru.glavs.hw014.io;

public interface IOService {
    void println(String message);

    void printf(String message, Object... args);

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);

    String readStringNoPrompt();
}
