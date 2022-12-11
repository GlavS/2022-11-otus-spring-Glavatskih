package ru.otus.glavs.service.helper;

public interface ConsoleHelper {
    int readInt();

    String readString();

    void writeMessage(String message);

    void write(String string);

    String readStringWithPrompt(String prompt);

    int readIntWithPrompt(String prompt);
}
