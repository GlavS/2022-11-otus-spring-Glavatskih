package ru.glavs.hw008.mongock.jsondata;

import java.util.List;

public interface JsonFileParser {
    List<String> parseJson(String filename);
}
