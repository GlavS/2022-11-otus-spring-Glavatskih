package ru.otus.glavs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.csv-file")

public class CsvFile {
    private String rusFilename;
    private String enFilename;
    private String defaultFilename;

    public String getRusFilename() {
        return rusFilename;
    }

    public void setRusFilename(String rusFilename) {
        this.rusFilename = rusFilename;
    }

    public String getEnFilename() {
        return enFilename;
    }

    public void setEnFilename(String enFilename) {
        this.enFilename = enFilename;
    }

    public String getDefaultFilename() {
        return defaultFilename;
    }

    public void setDefaultFilename(String defaultFilename) {
        this.defaultFilename = defaultFilename;
    }
}
