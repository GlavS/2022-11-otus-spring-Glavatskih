package ru.otus.glavs.service.loader;

import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.ApplicationLocale;
import ru.otus.glavs.properties.CsvFile;

@Component
public class CvsFilenameProvider implements FilenameProvider {
    private static final String LANG_RUSSIAN = "ru";
    private static final String LANG_ENGLISH = "en";
    private final ApplicationLocale applicationLocale;
    private final CsvFile csvFile;

    public CvsFilenameProvider(ApplicationLocale applicationLocale, CsvFile csvFile) {
        this.applicationLocale = applicationLocale;
        this.csvFile = csvFile;
    }

    @Override
    public String getFilename() {
        String currentLanguage = applicationLocale.getLocale().getLanguage();
        switch (currentLanguage) {
            case LANG_RUSSIAN:
                return csvFile.getRusFilename();
            case LANG_ENGLISH:
                return csvFile.getEnFilename();
            default:
                return csvFile.getDefaultFilename();
        }
    }
}
