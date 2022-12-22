package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.LocaleProperties;

import java.util.Locale;

@Component
public class LocalizedMessagesProvider implements LocalizedMessages {
    protected final MessageSource locMessage;
    protected final Locale locale;

    public LocalizedMessagesProvider(LocaleProperties localeProps, MessageSource locMessage) {
        this.locMessage = locMessage;
        this.locale = localeProps.getLocale();
    }

    @Override
    public String getTextMessage(String propCode) {
        return locMessage.getMessage(propCode, null, locale);
    }
}
