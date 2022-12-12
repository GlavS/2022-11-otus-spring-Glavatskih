package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;

import java.util.Locale;

@Component
public class LocalizedFilenameStorage {
    private final MessageSource locMessage;
    private final Locale locale;

    public LocalizedFilenameStorage(Application props, MessageSource locMessage) {
        this.locMessage = locMessage;
        this.locale = props.getLocale();
    }

    public String getFilename() {
        return locMessage.getMessage("filename", null, locale);
    }
}
