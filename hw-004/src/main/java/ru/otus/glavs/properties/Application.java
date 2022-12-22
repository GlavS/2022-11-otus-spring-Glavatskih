package ru.otus.glavs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "application")
public class Application {
    private int minCorrectAnswers;
    private Locale locale;
    private Map<String, String> csvFiles;

    public int getMinCorrectAnswers() {
        return minCorrectAnswers;
    }

    public void setMinCorrectAnswers(int minCorrectAnswers) {
        this.minCorrectAnswers = minCorrectAnswers;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Map<String, String> getCsvFiles() {
        return csvFiles;
    }

    public void setCsvFiles(Map<String, String> csvFiles) {
        this.csvFiles = csvFiles;
    }
}
