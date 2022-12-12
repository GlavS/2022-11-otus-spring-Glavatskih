package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
@Component
public class LocalizedConsoleHelperMessagesStorage extends LocalizedMessagesStorage {
    public LocalizedConsoleHelperMessagesStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }

    public String getReadIntMessage() {
        return locMessage.getMessage("consolehelper.readint.message", null, locale);
    }

    public String getReadStringMessage() {
        return locMessage.getMessage("consolehelper.readstring.message", null, locale);
    }
}
