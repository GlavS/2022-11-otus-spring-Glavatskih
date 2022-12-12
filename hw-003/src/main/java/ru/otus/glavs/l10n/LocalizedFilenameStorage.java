package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import ru.otus.glavs.properties.Application;

public class LocalizedFilenameStorage extends LocalizedMessagesStorage {
    public LocalizedFilenameStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }

    public String getFilename() {
        return locMessage.getMessage("filename", null, locale);
    }
}
