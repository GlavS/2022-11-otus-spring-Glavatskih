package ru.otus.glavs.service.loader;

import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
import ru.otus.glavs.properties.CsvFile;

@Component
public class CvsFilenameProvider implements FilenameProvider{
    private final Application application;
    private final CsvFile csvFile;

    public CvsFilenameProvider(Application application, CsvFile csvFile) {
        this.application = application;
        this.csvFile = csvFile;
    }

    @Override
    public String getFilename() {
        if(application.getLocale().getLanguage().equals("ru")){
            return csvFile.getRusFilename();
        } else {
            return csvFile.getEnFilename();
        }
    }
}
