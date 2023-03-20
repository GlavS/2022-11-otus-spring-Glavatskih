package ru.glavs.hw011.dbinit.loader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class JsonFileLoaderImpl implements JsonFileLoader {
    private ClassPathResource resource;

    @Override
    public void setJsonFile(String filename) {
        resource = new ClassPathResource(filename);
    }

    @Override
    public String getRawData() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot open resource file: " + e.getMessage(), e);
        }
        return sb.toString();
    }
}
