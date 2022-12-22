package ru.otus.glavs.properties;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
@Component
public class FilenameProviderPropertiesConfig implements FilenameProviderProperties {
    private final Application appProps;

    public FilenameProviderPropertiesConfig(Application appProps) {
        this.appProps = appProps;
    }

    @Override
    public Locale getLocale() {
        return appProps.getLocale();
    }

    @Override
    public Map<String, String> getCsvFiles() {
        return appProps.getCsvFiles();
    }
}
