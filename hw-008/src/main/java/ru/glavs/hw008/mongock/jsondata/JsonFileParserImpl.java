package ru.glavs.hw008.mongock.jsondata;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonFileParserImpl implements JsonFileParser {

    private final JsonFileLoader loader;


    public JsonFileParserImpl(JsonFileLoader loader) {
        this.loader = loader;
    }

    @Override
    public List<String> parseJson(String filename){
        List<String> result = new ArrayList<>();

        loader.setJsonFile(filename);
        String jsonString = loader.getRawData();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                result.add(jsonObject.toString());
            }
        } catch (JSONException e) {
            throw new RuntimeException("Error parsing JSON file: " + e.getMessage(), e);
        }
        return result;
    }
}
