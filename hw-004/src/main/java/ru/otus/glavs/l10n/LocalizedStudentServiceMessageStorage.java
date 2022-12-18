package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
@Component
public class LocalizedStudentServiceMessageStorage extends LocalizedMessagesStorage{
    public LocalizedStudentServiceMessageStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }

    public String getRegisterFirstNamePrompt() {
        return locMessage.getMessage("studentservice.register.firstname.prompt", null, locale);
    }

    public String getRegisterLastNamePrompt() {
        return locMessage.getMessage("studentservice.register.lastname.prompt", null, locale);
    }
}
