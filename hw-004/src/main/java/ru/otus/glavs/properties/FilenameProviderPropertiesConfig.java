package ru.otus.glavs.properties;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class FilenameProviderPropertiesConfig implements FilenameProviderProperties {
    private final Application appProps;
    private final LocaleProperties locProps;

    public FilenameProviderPropertiesConfig(Application appProps, LocaleProperties locProps) {
        this.appProps = appProps;
        this.locProps = locProps;
    }

    @Override
    public Locale getLocale() {
        return locProps.getLocale();
    }

    @Override
    public Map<String, String> getCsvFiles() {
        return appProps.getCsvFiles();
    }
}
