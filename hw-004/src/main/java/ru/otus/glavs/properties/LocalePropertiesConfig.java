package ru.otus.glavs.properties;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocalePropertiesConfig implements LocaleProperties {
    private final Application appProps;

    public LocalePropertiesConfig(Application appProps) {
        this.appProps = appProps;
    }

    @Override
    public Locale getLocale() {
        return appProps.getLocale();
    }
}
