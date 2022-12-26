package ru.otus.glavs.service.loader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("В классе TextFileLoader")
class TextFileLoaderTest {

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private CsvFilenameProvider config;

    @Autowired
    private TextFileLoader loader;

    @Test
    @DisplayName("метод getRowData должен корректно вычитать текстовый файл")
    void getRowDataMethodShouldCorrectlyReadFile() {
        when(config.getFilename()).thenReturn("TESTquizEN.csv");
        assertThat(loader.getRowData()).contains("Test question 1");
    }

    @Configuration
    @ComponentScan("ru.otus.glavs.service.loader")
    public static class NestedConf {

    }
}