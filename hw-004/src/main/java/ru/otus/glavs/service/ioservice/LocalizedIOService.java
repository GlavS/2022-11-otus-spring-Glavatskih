package ru.otus.glavs.service.ioservice;

import java.util.Locale;

public interface LocalizedIOService {

    String readStringWithPrompt(String propCode);

    int readIntWithPrompt(String propCode);

}
