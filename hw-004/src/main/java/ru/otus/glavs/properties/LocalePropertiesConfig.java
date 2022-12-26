package ru.otus.glavs.properties;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class LocalePropertiesConfig implements LocaleProperties {
    private final Application appProps;
    private Locale locale;

    public LocalePropertiesConfig(Application appProps) {
        this.appProps = appProps;
        this.locale = appProps.getLocale();
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public void changeDefaultLocale(Locale locale) throws LocaleNotSupportedException {
        Map<String, String> csvFiles = appProps.getCsvFiles();
        if(!csvFiles.containsKey(locale.toString())){
            throw new LocaleNotSupportedException("Locale " + locale + " is not supported");
        }
        this.locale = locale;
    }
}
