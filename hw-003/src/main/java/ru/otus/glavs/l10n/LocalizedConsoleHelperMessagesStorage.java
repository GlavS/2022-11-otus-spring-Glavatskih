package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;

import java.util.Locale;

@Component
public class LocalizedConsoleHelperMessagesStorage {
    private final MessageSource locMessage;
    private final Locale locale;

    public LocalizedConsoleHelperMessagesStorage(Application props, MessageSource locMessage) {
        this.locMessage = locMessage;
        this.locale = props.getLocale();
    }

    public String getReadIntMessage() {
        return locMessage.getMessage("consolehelper.readint.message", null, locale);
    }

    public String getReadStringMessage() {
        return locMessage.getMessage("consolehelper.readstring.message", null, locale);
    }
}
