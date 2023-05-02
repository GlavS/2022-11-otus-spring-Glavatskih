package ru.glavs.hw016.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class StreamIOService implements IOService {
    private final PrintStream out;
    private final InputStream in;

    public StreamIOService(@Value("#{T(java.lang.System).out}") PrintStream out,
                           @Value("#{T(java.lang.System).in}") InputStream in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void println(String message) {
        out.println(message);
    }

    @Override
    public void printf(String message, Object... args) {
        out.printf(message, args);
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        println(prompt);
        return readInt();
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        println(prompt);
        return readString();
    }

    @Override
    public String readStringNoPrompt() {
        return readString();
    }

    private int readInt() {
        int result;
        try {
            result = Integer.parseInt(readString());
        } catch (NumberFormatException exception) {
            println("Error reading int number, please retry...");
            return readInt();
        }
        return result;
    }

    private String readString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String result;
        try {
            result = reader.readLine();
        } catch (IOException exception) {
            println("Error reading your string, please retry...");
            return readString();
        }
        return result;
    }
}
