package ru.otus.glavs.l10n;

import java.util.Locale;

public interface LocalizedMessages {
    String getTextMessage(String propCode);
    void changeDefaultLocale(Locale locale) throws LocaleNotSupportedException;
    Locale getLocale();
}
