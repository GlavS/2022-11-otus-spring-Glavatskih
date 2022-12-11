package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;

@Component
public class LocalizedFilenameStorage {
    private final String filename;

    public LocalizedFilenameStorage(Application props, MessageSource locMessage) {
        filename = locMessage.getMessage("filename", null, props.getLocale());
    }

    public String getFilename() {
        return filename;
    }
}
