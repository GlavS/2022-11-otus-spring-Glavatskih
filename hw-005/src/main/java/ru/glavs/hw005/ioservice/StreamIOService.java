package ru.glavs.hw005.ioservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class StreamIOService implements IOService {
    private final PrintStream out;

    public StreamIOService(@Value("#{T(java.lang.System).out}") PrintStream out) {
        this.out = out;
    }

    @Override
    public void print(String message) {
        out.print(message);
    }

    @Override
    public void println(String message) {
        out.println(message);
    }

    @Override
    public void printf(String message, Object... args) {
        out.printf(message, args);
    }
}
