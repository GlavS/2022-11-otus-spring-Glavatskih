package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
import ru.otus.glavs.service.ioservice.IOService;

import java.util.Locale;
import java.util.Map;

@Component
public class LocalizedMessagesProvider implements LocalizedMessages {
    private final MessageSource locMessage;
    private final Application appProps;
    private final IOService ioService;
    private Locale locale;

    public LocalizedMessagesProvider(Application appProps, MessageSource locMessage, IOService ioService) {
        this.locMessage = locMessage;
        this.appProps = appProps;
        this.locale = appProps.getLocale();
        this.ioService = ioService;
    }

    @Override
    public String getTextMessage(String propCode) {
        return locMessage.getMessage(propCode, null, locale);
    }

    @Override
    public void changeDefaultLocale(Locale locale) {
        Map<String, String> csvFiles = appProps.getCsvFiles();
        if (locale.equals(this.locale)) {
            ioService.writeln("locale is already set");
        } else if (!csvFiles.containsKey(locale.toString())) {
            ioService.writeln("locale does not implemented yet");
        } else {
            this.locale = locale;
        }
    }
}
