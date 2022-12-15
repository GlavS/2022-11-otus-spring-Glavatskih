package ru.otus.glavs.service.loader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.l10n.LocalizedFilenameStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("TextFileLoader class")
@ExtendWith(MockitoExtension.class)
class TextFileLoaderTest {
    @Mock
    private LocalizedFilenameStorage storage;

    @Test
    @DisplayName("getRowData method should correctly read resource file")
    void getRowDataTest() {
        String testcontent = "1;Q1;A1;A2;A3;2\n" +
                "2;Q2;A1;A2;A3;3" + System.lineSeparator();
        String filename = "testquiz.csv";
        when(storage.getFilename()).thenReturn(filename);

        TextFileLoader loader = new TextFileLoader(storage);
        String rowData = loader.getRowData();

        assertThat(rowData).contains("2;Q2;A1;A2;A3;3");
    }
}