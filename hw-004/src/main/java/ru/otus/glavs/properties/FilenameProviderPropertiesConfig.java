package ru.otus.glavs.properties;

import org.springframework.stereotype.Component;
import ru.otus.glavs.l10n.LocalizedMessages;

import java.util.Locale;
import java.util.Map;

@Component
public class FilenameProviderPropertiesConfig implements FilenameProviderProperties {
    private final Application appProps;
    private final LocalizedMessages messagesProvider;

    public FilenameProviderPropertiesConfig(Application appProps, LocalizedMessages messagesProvider) {
        this.appProps = appProps;
        this.messagesProvider = messagesProvider;
    }

    @Override
    public Locale getLocale() {
        return messagesProvider.getLocale();
    }

    @Override
    public Map<String, String> getCsvFiles() {
        return appProps.getCsvFiles();
    }
}
