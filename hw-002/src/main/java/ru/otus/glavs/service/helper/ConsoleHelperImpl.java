package ru.otus.glavs.service.helper;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Service
public class ConsoleHelperImpl implements ConsoleHelper {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int readInt() {
        int result;
        try {
            result = Integer.parseInt(readString());
        } catch (NumberFormatException exception) {
            writeMessage("Error while enter number, please retry");
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
            writeMessage("Error while enter text, please retry");
            return readString();
        }

        return result;
    }

    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void write(String string) { //вывод без символа новой строки
        System.out.print(string);
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        writeMessage(prompt);
        return readString();
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        writeMessage(prompt);
        return readInt();
    }
}
