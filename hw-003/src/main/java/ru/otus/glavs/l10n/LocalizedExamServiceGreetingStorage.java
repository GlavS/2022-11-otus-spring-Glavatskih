package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
@Component
public class LocalizedExamServiceGreetingStorage extends LocalizedMessagesStorage{
    public LocalizedExamServiceGreetingStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }

    public String getExamServiceGreeting() {
        return locMessage.getMessage("examservicequiz.greeting", null, locale);
    }
}
