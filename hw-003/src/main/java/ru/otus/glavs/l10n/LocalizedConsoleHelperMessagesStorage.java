package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;

import java.util.Locale;

@Component
public class LocalizedConsoleHelperMessagesStorage {
    private final String readIntMessage;
    private final String readStringMessage;
    public LocalizedConsoleHelperMessagesStorage(Application props, MessageSource locMessage) {
        Locale locale = props.getLocale();
        this.readIntMessage = locMessage.getMessage("consolehelper.readint.message", null, locale);
        this.readStringMessage = locMessage.getMessage("consolehelper.readstring.message", null, locale);
    }

    public String getReadIntMessage() {
        return readIntMessage;
    }

    public String getReadStringMessage() {
        return readStringMessage;
    }
}
