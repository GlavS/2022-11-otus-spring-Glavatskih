package ru.otus.glavs.service.loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.glavs.properties.FilenameProviderProperties;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
@DisplayName("В классе CsvFilenameProvider")
class CsvFilenameProviderTest {

    @Autowired
    private CsvFilenameProvider csvFilenameProvider;
    @MockBean
    private FilenameProviderProperties filenameProps;

    private final Map<String, String> localizedCsvFiles = new HashMap<>();

    @BeforeEach
    void init() {

        localizedCsvFiles.put("en", "EnFile.csv");
        localizedCsvFiles.put("ru_RU", "RuFile.csv");
    }

    @DisplayName("метод getFilename должен возвращать имя файла согласно локали")
    @ParameterizedTest
    @MethodSource
    void getFilenameShouldReturnCorrectFilename(Locale locale, String filename) {

        when(filenameProps.getCsvFiles()).thenReturn(localizedCsvFiles);
        when(filenameProps.getLocale()).thenReturn(locale);
        assertThat(csvFilenameProvider.getFilename()).isEqualToIgnoringCase(filename);
    }

    private static Stream<Arguments> getFilenameShouldReturnCorrectFilename() {
        return Stream.of(
                Arguments.of(new Locale("en"), "EnFile.csv"),
                Arguments.of(new Locale("ru", "RU"), "RuFile.csv")
        );
    }

    @Configuration
    @ComponentScan({"ru.otus.glavs.service.loader", "ru.otus.glavs.properties"})
    public static class NestedConf {

    }
}