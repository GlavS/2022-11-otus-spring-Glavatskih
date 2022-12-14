package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;

import java.util.Locale;
@Component
public class LocalizedMessagesStorage {
    protected final MessageSource locMessage;
    protected final Locale locale;
    public LocalizedMessagesStorage(Application props, MessageSource locMessage) {
        this.locMessage = locMessage;
        this.locale = props.getLocale();
    }
}
