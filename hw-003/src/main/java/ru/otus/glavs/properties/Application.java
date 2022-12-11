package ru.otus.glavs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class Application {
    private String filename;
    private int minCorrectAnswers;
    private String locale;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getMinCorrectAnswers() {
        return minCorrectAnswers;
    }

    public void setMinCorrectAnswers(int minCorrectAnswers) {
        this.minCorrectAnswers = minCorrectAnswers;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
