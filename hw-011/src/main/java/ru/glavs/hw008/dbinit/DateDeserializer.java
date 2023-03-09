package ru.glavs.hw008.dbinit;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JacksonException {
        JsonNode date = (JsonNode)jsonParser.readValueAsTree().get("$date");
        String textDate = date.asText();
        try {
            return formatter.parse(textDate);
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse json date field" + e.getMessage());
        }
    }
}
