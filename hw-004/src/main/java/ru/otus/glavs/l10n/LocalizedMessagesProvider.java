package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;

import java.util.Locale;
import java.util.Map;

@Component
public class LocalizedMessagesProvider implements LocalizedMessages {
    private final MessageSource locMessage;
    private final Application appProps;
    private Locale locale;

    public LocalizedMessagesProvider(Application appProps, MessageSource locMessage) {
        this.locMessage = locMessage;
        this.appProps = appProps;
        this.locale = appProps.getLocale();
    }

    @Override
    public String getTextMessage(String propCode) {
        return locMessage.getMessage(propCode, null, locale);
    }

    @Override
    public void changeDefaultLocale(Locale locale) throws LocaleNotSupportedException{
        Map<String, String> csvFiles = appProps.getCsvFiles();
        if(!csvFiles.containsKey(locale.toString())){
            throw new LocaleNotSupportedException("Locale " + locale + " is not supported");
        }
            this.locale = locale;
    }
    @Override
    public Locale getLocale() {
        return locale;
    }
}
