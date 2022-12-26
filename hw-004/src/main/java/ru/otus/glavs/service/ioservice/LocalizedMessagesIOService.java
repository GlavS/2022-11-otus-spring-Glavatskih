package ru.otus.glavs.service.ioservice;

import org.springframework.stereotype.Component;
import ru.otus.glavs.l10n.LocalizedMessages;

@Component
public class LocalizedMessagesIOService implements LocalizedIOService {
    private final StreamIOService ioService;
    private final LocalizedMessages messagesProvider;

    public LocalizedMessagesIOService(StreamIOService ioService, LocalizedMessages messagesProvider) {
        this.ioService = ioService;
        this.messagesProvider = messagesProvider;
    }

    @Override
    public String readStringWithPrompt(String propCode) {
        ioService.writeln(messagesProvider.getTextMessage(propCode));
        return ioService.readString();
    }

    @Override
    public int readIntWithPrompt(String propCode) {
        ioService.writeln(messagesProvider.getTextMessage(propCode));
        return ioService.readInt();
    }

    @Override
    public void writeMessage(String propCode) {
        ioService.writeln(messagesProvider.getTextMessage(propCode));
    }
}
