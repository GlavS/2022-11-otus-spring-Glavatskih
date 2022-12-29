package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.LocaleProperties;

@Component
public class LocalizedMessagesProvider implements LocalizedMessages {
    private final MessageSource locMessage;
    private final LocaleProperties locProps;

    public LocalizedMessagesProvider(LocaleProperties locProps, MessageSource locMessage) {
        this.locMessage = locMessage;
        this.locProps = locProps;
    }

    @Override
    public String getTextMessage(String propCode) {
        return locMessage.getMessage(propCode, null, locProps.getLocale());
    }
}
