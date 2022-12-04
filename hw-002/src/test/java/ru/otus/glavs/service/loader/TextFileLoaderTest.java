package ru.otus.glavs.service.loader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
@DisplayName("TextFileLoader class")
class TextFileLoaderTest {

    @Test
    @DisplayName("getRowData method should correctly read resource file")
    void getRowDataTest() {
        String filename = "testquiz.csv";
        TextFileLoader loader = new TextFileLoader(filename);
        String rowData = loader.getRowData();

        assertThat(rowData).hasSize(34)
                .startsWith("1;Q1")
                .endsWith("A3;3\r\n");
    }
}