package ru.otus.glavs.properties;

import java.util.Locale;

public interface LocaleProperties {
    Locale getLocale();
    void changeDefaultLocale(Locale locale) throws LocaleNotSupportedException;
}
