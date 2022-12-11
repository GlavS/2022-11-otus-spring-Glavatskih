package ru.otus.glavs.service.loader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@PropertySource("classpath:application.yml")
public class TextFileLoader implements Loader {
    private final String csvFileName;

    public TextFileLoader(@Value("${quiz.filename}") String csvFileName) {
        this.csvFileName = csvFileName;
    }

    @Override
    public String getRowData() {
        StringBuilder sb = new StringBuilder();
        ClassPathResource resource = new ClassPathResource(csvFileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
