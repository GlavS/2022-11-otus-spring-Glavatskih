package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
@Component
public class LocalizedExamProcessorMessagesStorage extends LocalizedMessagesStorage {

    public LocalizedExamProcessorMessagesStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }

    public String getCollectAnswersPrompt() {
        return locMessage.getMessage("examprocessor.collectanswers.prompt", null, locale);
    }

    public String getCollectAnswersInvalid() {
        return locMessage.getMessage("examprocessor.collectanswers.invalid", null, locale);
    }
}
