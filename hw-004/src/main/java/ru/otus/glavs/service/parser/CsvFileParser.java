package ru.otus.glavs.service.parser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.loader.Loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvFileParser implements Parser {
    private static final int ID = 0;
    private static final int QUESTION = 1;
    private static final int ANSWER1 = 2;
    private static final int ANSWER2 = 3;
    private static final int ANSWER3 = 4;
    private static final int CORRECT_ANSWER = 5;

    private final Loader loader;

    public CsvFileParser(Loader loader) {
        this.loader = loader;
    }

    @Override
    public List<Quiz> parse() {
        List<Quiz> result = new ArrayList<>();
//We are to use jackson-dataformat-csv library to parse Quiz resource
//https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv
//https://cowtowncoder.medium.com/reading-csv-with-jackson-c4e74a15ddc1
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema()
                .withColumnSeparator(';');

        try (MappingIterator<List<String>> iterator = mapper
                .readerForListOf(String.class)
                .with(com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.WRAP_AS_ARRAY)
                .with(schema)
                .readValues(loader.getRowData())) {
            while (iterator.hasNextValue()) {
                List<String> row = iterator.nextValue();
                Quiz quiz = new Quiz(
                        Integer.parseInt(row.get(ID)), // We don't use ID for now, reserving for future tasks
                        row.get(QUESTION),
                        row.get(ANSWER1),
                        row.get(ANSWER2),
                        row.get(ANSWER3),
                        Integer.parseInt(row.get(CORRECT_ANSWER)));
                result.add(quiz);
            }
        } catch (IOException e) {
            throw new CsvFileParserException("Error parsing .csv file: ", e);
        }


        return result;
    }


}
