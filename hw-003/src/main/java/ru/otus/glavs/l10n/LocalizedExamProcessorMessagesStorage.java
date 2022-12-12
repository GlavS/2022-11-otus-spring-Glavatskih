package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import ru.otus.glavs.properties.Application;

public class LocalizedExamProcessorMessagesStorage extends LocalizedMessagesStorage {

    public LocalizedExamProcessorMessagesStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }
}
