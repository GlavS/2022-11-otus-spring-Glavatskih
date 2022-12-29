package ru.otus.glavs.service.ioservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.glavs.l10n.LocalizedMessages;

import java.io.*;


@Service
public class StreamIOService implements IOService {
    private final LocalizedMessages localizedMessages;
    private final BufferedReader reader;
    private final PrintStream out;

    public StreamIOService(@Value("#{ T(java.lang.System).out }") PrintStream out,
                           @Value("#{ T(java.lang.System).in }") InputStream in,
                           LocalizedMessages localizedMessages) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
        this.localizedMessages = localizedMessages;
    }

    @Override
    public int readInt() {
        int result;
        try {
            result = Integer.parseInt(readString());
        } catch (NumberFormatException exception) {
            writeln(localizedMessages.getTextMessage("consolehelper.readint.message"));
            return readInt();
        }
        return result;
    }

    @Override
    public String readString() {
        String result;
        try {
            result = reader.readLine();
        } catch (IOException exception) {
            writeln(localizedMessages.getTextMessage("consolehelper.readstring.message"));
            return readString();
        }

        return result;
    }

    @Override
    public void writeln(String message) {
        this.out.println(message);
    }

    @Override
    public void write(String string) { //вывод без символа новой строки
        this.out.print(string);
    }
}
