package ru.otus.glavs.service.loader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class TextFileLoader implements Loader {
    private final String csvFileName;

    public TextFileLoader(FilenameProvider filenameProvider) {
        this.csvFileName = filenameProvider.getFilename();
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
            throw new TextFileLoaderException("Error reading resource text file", e);
        }
        return sb.toString();
    }
}
