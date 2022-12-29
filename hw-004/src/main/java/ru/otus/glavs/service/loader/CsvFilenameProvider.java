package ru.otus.glavs.service.loader;

import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.FilenameProviderProperties;

import java.util.Map;

@Component
public class CsvFilenameProvider implements FilenameProvider {

    private final FilenameProviderProperties filenameProps;

    public CsvFilenameProvider(FilenameProviderProperties filenameProps) {
        this.filenameProps = filenameProps;
    }

    @Override
    public String getFilename() {
        String currentLanguage = filenameProps.getLocale().toString();
        Map<String, String> localizedCsvFiles = filenameProps.getCsvFiles();
        return localizedCsvFiles.get(currentLanguage);
    }
}
