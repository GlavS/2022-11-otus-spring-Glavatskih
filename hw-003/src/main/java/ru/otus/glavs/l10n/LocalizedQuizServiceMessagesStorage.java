package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
@Component
public class LocalizedQuizServiceMessagesStorage extends LocalizedMessagesStorage{
    public LocalizedQuizServiceMessagesStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }

    public String getDisplayQuestion() {
        return locMessage.getMessage("quizservice.display.question", null, locale);
    }

    public String getDisplayVariant() {
        return locMessage.getMessage("quizservice.display.variant", null, locale);
    }

    public String getAnswerError() {
        return locMessage.getMessage("quizservice.answerbynumber.error", null, locale);
    }
}
