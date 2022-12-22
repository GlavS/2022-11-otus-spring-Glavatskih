package ru.otus.glavs.properties;

import java.util.Locale;
import java.util.Map;

public interface FilenameProviderProperties {
    Locale getLocale();

    Map<String, String> getCsvFiles();
}
