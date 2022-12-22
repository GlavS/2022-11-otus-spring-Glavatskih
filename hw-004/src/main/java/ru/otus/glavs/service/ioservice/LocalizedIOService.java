package ru.otus.glavs.service.ioservice;

public interface LocalizedIOService {

    String readStringWithPrompt(String propCode);

    int readIntWithPrompt(String propCode);
}
