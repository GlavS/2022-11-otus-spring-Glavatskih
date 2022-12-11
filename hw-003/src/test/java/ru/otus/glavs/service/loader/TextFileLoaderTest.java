package ru.otus.glavs.service.loader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.properties.Application;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TextFileLoader class")
@ExtendWith(MockitoExtension.class)
class TextFileLoaderTest {
    @Mock
    private Application props;
    @Test
    @DisplayName("getRowData method should correctly read resource file")
    void getRowDataTest() {
        String filename = "testquiz.csv";

        TextFileLoader loader = new TextFileLoader(props);
        String rowData = loader.getRowData();

        assertThat(rowData).hasSize(34)
                .startsWith("1;Q1")
                .endsWith("A3;3\r\n");
    }
}