package ru.otus.glavs.service.helper;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Утилитный класс. Может быть, сделать все методы статическими?
@Service
public class ConsoleHelper {
    private  final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public  int readInt() {
        int result = 0;
        try {
            result = Integer.parseInt(readString());
        } catch (NumberFormatException exception) {
            writeMessage("Error while enter number, please retry");
            return readInt();
        }
        return result;
    }

    public  String readString() {
        String result = "";
        try {
            result = reader.readLine();
        } catch (IOException exception) {
            writeMessage("Error while enter text, please retry");
            return readString();
        }

        return result;
    }

    public  void writeMessage(String message) {
        System.out.println(message);
    }
    public  void write(String string){ //вывод без символа новой строки
        System.out.print(string);
    }

    public String readStringWithPrompt(String prompt){
        writeMessage(prompt);
        return readString();
    }

    public int readIntWithPrompt(String prompt){
        writeMessage(prompt);
        return readInt();
    }
}
